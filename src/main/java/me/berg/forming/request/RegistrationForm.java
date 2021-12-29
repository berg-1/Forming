package me.berg.forming.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.berg.forming.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("注册请求表单")
public class RegistrationForm {

    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "用户名称", required = true)
    private String name;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        List<String> role = Collections.singletonList("ROLE_USER");
        return new UserEntity(userId, name, passwordEncoder.encode(password), role);
    }
}
