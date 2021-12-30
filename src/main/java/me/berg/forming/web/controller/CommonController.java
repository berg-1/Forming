package me.berg.forming.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.berg.forming.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "常用API")
@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class CommonController {

    @ApiOperation("测试用户是否具有访问 /user/** 的权限 直接访问即可")
    @GetMapping("/user/hello")
    public Result<Object> userCommonRequest() {
       return Result.success("有权访问 User API");
    }

    @ApiOperation("测试用户是否具有访问 /admin/** 的权限 直接访问即可")
    @GetMapping("/admin/hello")
    public Result<Object> adminCommonRequest() {
        return Result.success("有权访问 Admin API");
    }
}
