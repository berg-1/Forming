package me.berg.forming.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.request.UpdateUserRequest;
import me.berg.forming.service.UserService;
import me.berg.forming.util.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "用户控制")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation("当前登录用户详情")
    @GetMapping("/current/detail")
    public Result<UserEntity> queryCurrentUser(@AuthenticationPrincipal UserEntity user) {
        return Result.success(user, "获取成功!");
    }


    @ApiOperation("修改当前用户信息")
    @PostMapping(value = "/update")
    public Result<Object> updateUser(@ApiParam(name = "userEntity", value = "Message to send", required = true) @RequestBody UserEntity userEntity,
                                     @ApiParam(name = "userId", value = "用户ID") @RequestAttribute String userId) {
        return Result.success(userService.updateById(userEntity) + "@" + userId);
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/update/password")
    public Result<Object> updatePassword(@ApiParam(name = "request", value = "修改密码实体（包含旧密码、新密码、新密码重复）")
                                                 UpdateUserRequest.Password request,
                                         @ApiIgnore @AuthenticationPrincipal UserEntity user) {
        if (!user.getPassword().equals(passwordEncoder.encode(request.getOldPassword()))) {
            log.info("User Password: " + user.getPassword() + ", Request Old Password: " + request.getOldPassword());
            return Result.failed("旧密码错误");
        }
        if (!request.getPassword().equals(request.getRepeatPassword())) {
            log.info("Request Old Password: " + request.getOldPassword() + "Request New Password: " + request.getPassword());
            return Result.failed("两次密码不一致");
        }
        if (userService.updatePassword(user.getUserId(), request.getPassword())) {
            log.info("User Password: " + user.getPassword() + ", Request Old Password: " + request.getOldPassword());
            return Result.success(null, "修改成功!");
        }
        return Result.failed();
    }


}
