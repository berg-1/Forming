package me.berg.forming.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.mapper.UserMapper;
import me.berg.forming.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 用户表服务实现类
 */
@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity getUserById(String username) {
        return new UserEntity();
    }

    @Override
    public Boolean updatePassword(String userId, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setPassword(DigestUtil.sha256Hex(password));
        return this.updateById(userEntity);
    }

    @Override
    public Boolean updateLoginTime(String userId, LocalDateTime now) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setLastLoginTime(now);
        return this.updateById(user);
    }
}


