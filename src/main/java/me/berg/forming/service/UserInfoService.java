package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.UserInfo;

import java.util.List;

/**
 * UserInfo 相关业务
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return User Info 的 List
     */
    List<UserInfo> getUserInfos(String userId);

    /**
     * 不存在ProjectItem，否则更新
     */
    void saveOrUpdate(String userId, Integer type, String content);

}
