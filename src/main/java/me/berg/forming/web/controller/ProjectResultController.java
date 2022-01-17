package me.berg.forming.web.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.entity.*;
import me.berg.forming.service.ProjectItemService;
import me.berg.forming.service.ProjectResultService;
import me.berg.forming.service.ProjectService;
import me.berg.forming.service.UserInfoService;
import me.berg.forming.util.Result;
import me.berg.forming.util.ResultCode;
import me.berg.forming.web.vo.ProjectLoadVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "表单提交控制")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectResultController {

    private final ProjectResultService resultService;
    private final ProjectService projectService;
    private final ProjectItemService projectItemService;
    private final UserInfoService userInfoService;

    @ApiOperation("获得项目数据和自动填写数据")
    @GetMapping("/share/{projectKey}")
    public Result<ProjectLoadVO> loadProject(@PathVariable String projectKey, @AuthenticationPrincipal UserEntity user) {
        List<ProjectItem> items = projectItemService.listByKey(projectKey);
        List<Integer> autoFillKeys = projectItemService.listAutoFillKeys(projectKey);
        List<UserInfo> infos;
        try {
            infos = userInfoService.getUserInfos(user.getUserId())
                    .stream()
                    .filter(k -> autoFillKeys.contains(k.getType()))
                    .collect(Collectors.toList());
            System.out.println(infos);
        } catch (NullPointerException e) {
            return Result.failed(ResultCode.USER_NOT_LOGIN, "请先登录!");
        }
        Project project = projectService.getByKey(projectKey);
        return Result.success(new ProjectLoadVO(project, items, infos));
    }

    /**
     * 提交填写结果
     *
     * @param result 填写结果
     * @param user   UserEntity
     * @return success 或 failed
     */
    @PostMapping("/user/result/submit")
    public Result<String> createProjectResult(@RequestBody ProjectResult result,
                                              @AuthenticationPrincipal UserEntity user) {
        result.setUserId(user.getUserId());
        // 获取自动填写的 item
        List<ProjectItem> autoFill = projectItemService.listAutoFill(result.getProjectKey());
        JSON json = JSONUtil.parse(result.getData());
        // 解析json中item的值，存入user_info表
        autoFill.forEach(item -> userInfoService.save(new UserInfo(user.getUserId(), json.getByPath(item.getPath()).toString(), item.getAutofill())));
        if (resultService.saveProjectResult(result)) {
            return Result.success(null, "提交成功");
        }
        return Result.failed("提交失败!");
    }

}
