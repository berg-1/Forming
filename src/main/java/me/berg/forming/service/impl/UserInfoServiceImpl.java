package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.UserInfo;
import me.berg.forming.mapper.UserInfoMapper;
import me.berg.forming.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Override
    public List<UserInfo> getUserInfos(String userId) {
        return this.list(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
    }

    @Override
    public void saveOrUpdate(String userId, Integer type, String content) {
        this.saveOrUpdate(new UserInfo(userId, content, type),
                Wrappers.<UserInfo>lambdaUpdate().eq(UserInfo::getUserId, userId).eq(UserInfo::getType, type));
    }
}




