package me.berg.forming.web.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.entity.ProjectItem;
import me.berg.forming.entity.ProjectResult;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.entity.UserInfo;
import me.berg.forming.service.ProjectItemService;
import me.berg.forming.service.ProjectResultService;
import me.berg.forming.service.ProjectService;
import me.berg.forming.service.UserInfoService;
import me.berg.forming.util.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "表单提交控制")
@Slf4j
@RestController
@RequestMapping("/user/result/")
@RequiredArgsConstructor
public class ProjectResultController {

    private final ProjectResultService resultService;
    private final ProjectService projectService;
    private final ProjectItemService projectItemService;
    private final UserInfoService userInfoService;

    /**
     * 创建填写结果
     *
     * @param result 填写结果
     * @param user   UserEntity
     * @return success 或 failed
     */
    @PostMapping("/create")
    public Result<String> createProjectResult(@RequestBody ProjectResult result,
                                              @AuthenticationPrincipal UserEntity user) {
        result.setUserId(user.getUserId());
        List<ProjectItem> autoFill = projectItemService.listAutoFill(result.getProjectKey());
        JSON json = JSONUtil.parse(result.getData());
        autoFill.forEach(item -> userInfoService.save(new UserInfo(user.getUserId(), json.getByPath(item.getPath()).toString(), item.getAutofill())));
        if (resultService.saveProjectResult(result)) {
            return Result.success(null, "提交成功");
        }
        return Result.failed("提交失败!");
    }

}
