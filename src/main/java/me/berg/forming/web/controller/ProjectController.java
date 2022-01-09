package me.berg.forming.web.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.entity.Project;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.service.ProjectItemService;
import me.berg.forming.service.ProjectService;
import me.berg.forming.util.Result;
import me.berg.forming.web.vo.ProjectDetailVO;
import me.berg.forming.web.vo.ProjectPublishVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "项目管理")
@Slf4j
@RestController
@RequestMapping("/user/project/")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectItemService projectItemService;

    /**
     * 创建项目模板
     */
    @ApiOperation("创建项目")
    @PostMapping("/create")
    public Result<String> createProjectTemplate(@RequestBody Project project, @AuthenticationPrincipal UserEntity user) {
        project.setKey(IdUtil.simpleUUID());
        project.setCreateTime(LocalDateTime.now());
        project.setUserId(user.getUserId());
        log.info("用户{}创建项目:{}, Authorities:{}. ", user.getUsername(), project.getKey(), user.getAuthorities());
        projectService.save(project);
        return Result.success(project.getKey(), "创建成功!");
    }

    /**
     * 发布项目
     */
    @ApiOperation("发布项目")
    @PostMapping("/publish")
    public Result<String> publishProject(@RequestBody ProjectPublishVO request) {
        String projectKey = request.getProjectKey();
        List<ProjectItem> items = request.getProjectItems();
        for (ProjectItem item : items) {
            projectItemService.saveItemByProjectKey(item, projectKey);
        }
        return Result.success(projectKey, "发布成功!");
    }

    /**
     * 查询单个项目(Project)
     */
    @ApiOperation("查询单个项目(Project)")
    @GetMapping("/items/{key}")
    public Result<Project> queryProject(@PathVariable String key, @AuthenticationPrincipal UserEntity user) {
        return Result.success(projectService.getByKey(key, user.getUserId()));
    }

    /**
     * 查询单个项目的表单项信息(Project Items)
     */
    @ApiOperation("查询单个项目(Project)")
    @GetMapping("/{key}")
    public Result<List<ProjectItem>> queryProjectItems(@PathVariable String key) {
        return Result.success(projectItemService.listByTemplateKey(key));
    }


    /**
     * 查询项目完整信息<br/>
     * 包含项目信息 项目表单项信息
     */
    @ApiOperation("查询项目完整信息 包含项目(Project)信息和项目表单项(Project Item)信息")
    @GetMapping("/details/{key}")
    public Result<Object> queryProjectDetails(@PathVariable String key, @AuthenticationPrincipal UserEntity user) {
        Project project = projectService.getByKey(key, user.getUserId());
        List<ProjectItem> projectItemList = null;
        if (project != null) {
            projectItemList = projectItemService.listByTemplateKey(key);
        }
        return Result.success(new ProjectDetailVO(project, projectItemList));
    }


    /**
     * 根据条件查询所有项目 只返回Project列表
     */
    @GetMapping("/list")
    @ApiOperation("根据条件查询所有项目 只返回Project列表")
    public Result<List<Project>> listProjects(@AuthenticationPrincipal UserEntity user) {
        List<Project> entityList = projectService.listById(user.getUserId());
        return Result.success(entityList);
    }

    /**
     * 将项目移入回收站
     */
    @ApiOperation("将项目移入回收站")
    @PostMapping("/delete")
    public Result<Boolean> deleteProject(@RequestBody Project request, @AuthenticationPrincipal UserEntity user) {
        if (projectService.deleteByKey(request.getKey(), user.getUserId()))
            return Result.success("删除成功!");
        return Result.failed("删除失败!");
    }

    /**
     * 查询回收站项目
     */
    @ApiOperation("查询回收站项目")
    @GetMapping("/recycle/page")
    public Result<List<Project>> queryRecycleProjects(@AuthenticationPrincipal UserEntity user) {
        List<Project> recycles = projectService.listRecycle(user.getUserId());
        return Result.success(recycles);
    }

}
