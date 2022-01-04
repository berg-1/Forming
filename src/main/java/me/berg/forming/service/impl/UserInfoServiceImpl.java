package me.berg.forming.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.berg.forming.entity.UserInfo;
import me.berg.forming.mapper.UserInfoMapper;
import me.berg.forming.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




