package me.berg.forming.component;

import me.berg.forming.entity.UserAuthority;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.service.UserAuthorityService;
import me.berg.forming.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SpringUserService implements UserDetailsService {

    private final UserService userService;
    private final UserAuthorityService authorityService;

    public SpringUserService(UserService userService, UserAuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    /**
     * 返回用户实体。设置权限
     *
     * @param userId 用户ID
     * @return UserDetails
     * @throws UsernameNotFoundException 没有找到用户名
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // TODO 自己调用数据库，对username进行查询，看看在数据库中是否存在, 将数据库中的username、password赋值给detailsService返回
        UserEntity user = userService.getById(userId);
        List<String> roles = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<UserAuthority> authorities = authorityService.listByMap(map);
        for (UserAuthority authority : authorities) {
            roles.add(authority.getRole());
        }
        user.setRoles(roles);
        return user;
    }
}
