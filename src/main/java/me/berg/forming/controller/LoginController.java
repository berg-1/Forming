package me.berg.forming.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import me.berg.forming.service.UserService;
import me.berg.forming.util.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录控制")
@RestController
@RequiredArgsConstructor
public class LoginController {

    final AuthenticationManager authenticationManager;
    final UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录，服务器进行反馈。")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败")
    })
    @PostMapping("/login")
    public Result<Object> login(@RequestParam @ApiParam(name = "username", value = "用户ID", required = true) String username,
                                @RequestParam @ApiParam(name = "password", value = "用户密码", required = true) String password) {
        return Result.success();
    }
}
