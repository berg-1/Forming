package me.berg.forming.web.controller;


import cn.hutool.core.util.IdUtil;
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
        try {
            projectService.save(project);
        } catch (Exception e) {
            return Result.failed("创建失败！");
        }
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
            Boolean success = projectItemService.saveItemByProjectKey(item, projectKey);
            if (!success) return Result.failed("发布失败，请检查发布信息是否正确", projectKey);
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
    @PostMapping("/recycle")
    public Result<Boolean> deleteProject(@RequestBody Project request, @AuthenticationPrincipal UserEntity user) {
        if (projectService.recycleByKey(request.getKey(), user.getUserId()))
            return Result.success(null, "删除成功!");
        return Result.failed("删除失败!");
    }


    /**
     * 不移动至回收站，直接删除
     */
    @ApiOperation("不移动至回收站，直接删除")
    @PostMapping("/remove")
    public Result<String> deleteAnyway(@RequestBody Project project, @AuthenticationPrincipal UserEntity user) {
        boolean remove = projectItemService.deleteByKey(project.getKey(), user.getUserId());
        if (remove || projectItemService.listByTemplateKey(project.getKey()).isEmpty()) {
            if (projectService.removeAnyway(project.getKey(), user.getUserId()))
                return Result.success("项目删除成功");
            return Result.failed("项目删除失败，但表单项已删除!");
        }
        return Result.failed("项目删除失败!");
    }

    /**
     * 查询回收站项目
     */
    @ApiOperation("查询回收站项目")
    @GetMapping("/recycle/list")
    public Result<List<Project>> queryRecycleProjects(@AuthenticationPrincipal UserEntity user) {
        List<Project> recycles = projectService.listRecycle(user.getUserId());
        return Result.success(recycles);
    }

    /**
     * 从回收站中恢复项目
     */
    @ApiOperation("从回收站中恢复项目")
    @PostMapping("/recycle/restore")
    public Result<Boolean> restoreRecycleProject(@RequestBody Project request, @AuthenticationPrincipal UserEntity user) {
        if (projectService.restoreByKey(request.getKey(), user.getUserId()))
            return Result.success(null, "恢复成功!");
        return Result.failed("恢复失败!");
    }

    /**
     * 从回收站中删除项目
     */
    @ApiOperation("从回收站中删除项目")
    @PostMapping("/recycle/delete")
    public Result<String> deleteRecycleProject(@RequestBody Project project, @AuthenticationPrincipal UserEntity user) {
        boolean remove = projectItemService.deleteByKey(project.getKey(), user.getUserId());
        if (remove || projectItemService.listByTemplateKey(project.getKey()).isEmpty()) {
            if (projectService.deleteByKey(project.getKey(), user.getUserId()))
                return Result.success("项目删除成功");
            return Result.failed("项目删除失败，但表单项已删除!");
        }
        return Result.failed("项目删除失败!");
    }

}
