package me.berg.forming.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.berg.forming.util.Result;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分享的表单")
@RestController
@RequestMapping("/s")
@CrossOrigin("*")
public class ShareController {

    @ApiOperation("获得分享表单JSON数据")
    @GetMapping("/{shareId}")
    public Result<Object> getShareUrl(@ApiParam(name = "shareId", value = "分享表单的ID", required = true)
                                              @PathVariable String shareId) {
        return Result.success(shareId);
    }

}
