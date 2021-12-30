package me.berg.forming.web.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.request.RegistrationForm;
import me.berg.forming.entity.UserAuthority;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.exception.UsernameAlreadyExistsException;
import me.berg.forming.service.UserAuthorityService;
import me.berg.forming.service.UserService;
import me.berg.forming.util.ResultCode;
import me.berg.forming.util.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "注册管理")
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityService authorityService;


    @ApiOperation("新增用户接口（注册）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "java.lang.String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "java.lang.String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 999, message = "失败"),
            @ApiResponse(code = 2008, message = "用户已存在")
    })
    @PostMapping(path = "/register")
    public Result<Object> userRegistration(RegistrationForm form) {
        UserEntity userEntity = form.toUser(passwordEncoder);
        log.info("Register ID: " + userEntity.getUserId());
        log.info("Register PW: " + userEntity.getPassword());
        log.info("Register NA: " + userEntity.getName());
        try {
            UserEntity id = userService.getById(form.getUserId());
            if (id != null) {
                throw new UsernameAlreadyExistsException("用户已存在");
            }
            authorityService.save(new UserAuthority(null, userEntity.getUserId(), "ROLE_USER"));
            userService.save(form.toUser(passwordEncoder));
            return Result.success(null, "注册成功!");
        } catch (UsernameAlreadyExistsException e) {
            return Result.failed(ResultCode.USER_ACCOUNT_ALREADY_EXIST, "用户已存在");
        }
    }
}
