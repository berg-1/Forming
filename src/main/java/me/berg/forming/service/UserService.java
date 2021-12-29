package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.UserEntity;

/**
 * 用户(AcUser)表服务接口
 *
 * @author smalljop
 * @since 2020-11-10 18:10:42
 */
public interface UserService extends IService<UserEntity> {

    UserEntity getUserById(String username);

    Boolean updatePassword(String userId, String password);
}
