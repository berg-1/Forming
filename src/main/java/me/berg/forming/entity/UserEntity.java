package me.berg.forming.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApiModel(value = "用户实体类", description = "用户实体中包含用户相关的所有业务字段，如有需要请另行添加")
@Data
@NoArgsConstructor
@TableName("user")
public class UserEntity implements UserDetails {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 性别 : 1为男, 2为女, 0为未知
     */
    @ApiModelProperty(value = "性别", notes = "1为男, 2为女, 0为未知")
    private Integer gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @TableField(exist = false)
    private List<String> roles;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity(String userId, String name, String userPwd) {
        this.userId = userId;
        this.name = name;
        this.password = userPwd;
    }

    public UserEntity(String userId, String name, String encode, List<String> role) {
        this.userId = userId;
        this.name = name;
        this.password = encode;
        this.roles = role;
    }

    public UserEntity(String userId, String s) {
        this.userId = userId;
        this.password = s;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
