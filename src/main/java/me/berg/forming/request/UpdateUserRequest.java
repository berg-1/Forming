package me.berg.forming.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class UpdateUserRequest {

    @Data
    public static class Password {

        @ApiModelProperty(value = "旧密码", required = true)
        private String oldPassword;

        @ApiModelProperty(value = "新密码", required = true)
        private String password;

        @ApiModelProperty(value = "重复密码", required = true)
        private String repeatPassword;
    }

}
