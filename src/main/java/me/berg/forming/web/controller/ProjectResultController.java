package me.berg.forming.web.controller;

import lombok.RequiredArgsConstructor;
import me.berg.forming.entity.ProjectResult;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.service.ProjectResultService;
import me.berg.forming.service.ProjectService;
import me.berg.forming.util.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/result/")
@RequiredArgsConstructor
public class ProjectResultController {

    private final ProjectResultService resultService;
    private final ProjectService projectService;

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
        if (resultService.saveProjectResult(result)) {
            return Result.success(null, "提交成功");
        }
        return Result.failed("提交失败!");
    }

}
