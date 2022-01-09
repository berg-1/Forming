package me.berg.forming.web.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@RequestMapping("user/project/")
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
     * 查询项目模板详情
     * 包含项目信息 项目表单项信息
     */
    @ApiOperation("查询某个项目模板详情 包含项目(Project)信息和项目表单项(Project Item)信息")
    @GetMapping("/details/{key}")
    public Result<Object> queryProjectTemplateDetails(@PathVariable String key) {
        Project templateEntity = projectService.getByKey(key);
        List<ProjectItem> projectItemList = projectItemService.listByTemplateKey(key);
        return Result.success(new ProjectDetailVO(templateEntity, projectItemList));
    }


    /**
     * 根据条件查询所有项目 只返回Project列表
     */
    @GetMapping("/list")
    public Result<List<Project>> listProjects(@AuthenticationPrincipal UserEntity user) {
        List<Project> entityList = projectService.list(Wrappers.<Project>lambdaQuery().eq(Project::getUserId, user.getUserId())
                .orderByDesc(Project::getCreateTime));
        for (Project project : entityList) {
            log.info("Project {}, Name {}, Create Time {}", project.getKey(), project.getName(), project.getCreateTime());
        }
        return Result.success(entityList);
    }
}
