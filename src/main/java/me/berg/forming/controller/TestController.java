package me.berg.forming.controller;

import io.swagger.annotations.Api;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.util.ResultCode;
import me.berg.forming.util.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "测试管理")
@ApiIgnore
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello security!";
    }

    @GetMapping("/api/user/test")
    public Result<Object> userCommonRequest(@AuthenticationPrincipal UserEntity user) {
        System.out.println("User: " + user.getUsername() + " Authorities: " + user.getAuthorities());
        return new Result<>(ResultCode.SUCCESS);
    }

    @GetMapping("/api/admin/test")
    public Result<Object> adminCommonRequest(@AuthenticationPrincipal UserEntity user) {
        System.out.println("User: " + user.getUsername() + " Authorities: " + user.getAuthorities());
        return new Result<>(ResultCode.SUCCESS);
    }
}
