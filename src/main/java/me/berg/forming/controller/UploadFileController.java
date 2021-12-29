package me.berg.forming.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.berg.forming.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Api("文件上传")
public class UploadFileController {


    /**
     * 上传用户文件
     * <p>
     * 用户Id MD5加密 同一个用户的文件放在一个目录下
     *
     * @param file   文件
     * @param userId 用户ID
     * @return Result<String> 200：SUCCESS
     */
    @PostMapping("/user/file/upload")
    public Result<String> uploadUserFile(@RequestParam("file") MultipartFile file, @RequestAttribute Long userId) {
        return Result.success();
    }


    /**
     * 项目文件上传
     *
     * @param file       文件
     * @param projectKey 项目ID
     * @return Result<String> 200：SUCCESS
     */
    @PostMapping("/project/file/upload/{projectKey}")
    public Result<String> uploadProjectFile(@RequestParam("file") MultipartFile file, @PathVariable("projectKey") String projectKey) {
        return Result.success();
    }

}


