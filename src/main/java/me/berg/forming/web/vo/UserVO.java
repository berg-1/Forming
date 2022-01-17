package me.berg.forming.web.vo;

import lombok.Data;
import me.berg.forming.entity.UserEntity;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private String username;
    private String name;
    private Integer gender;
    private String phoneNumber;
    private String email;
    private LocalDateTime lastLoginTime;
    private String avatar;

    public UserVO(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.name = userEntity.getName();
        this.gender = userEntity.getGender();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.email = userEntity.getEmail();
        this.lastLoginTime = userEntity.getLastLoginTime();
        this.avatar = userEntity.getAvatar();
    }
}
