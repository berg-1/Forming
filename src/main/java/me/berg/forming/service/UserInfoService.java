package me.berg.forming.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.berg.forming.entity.UserInfo;
import me.berg.forming.web.vo.UserInfoVO;

import java.util.List;

/**
 *
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return User Info 的 List
     */
    List<UserInfo> getUserInfos(String userId);

}
