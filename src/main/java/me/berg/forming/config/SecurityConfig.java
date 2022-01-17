package me.berg.forming.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.berg.forming.component.CustomPasswordEncoder;
import me.berg.forming.entity.UserEntity;
import me.berg.forming.util.Result;
import me.berg.forming.util.ResultCode;
import me.berg.forming.web.vo.UserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final CustomPasswordEncoder myPasswordEncoder;

    /**
     * 描述: 注入AuthenticationManager管理器
     **/
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * http 相关的配置，包括登入登出、异常处理、会话管理等
     *
     * @param http HttpSecurity
     * @throws Exception Exceptions may happen
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(authenticationProvider())
                .httpBasic()
                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Result.failed(ResultCode.USER_NOT_LOGIN, "用户未登录!")));
                    out.flush();
                    out.close();
                })
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasRole("USER")  // 使用 /api/user/** 而不是 /api/user
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/register", "/register/**").permitAll()  // 注册
                .antMatchers("/s/**").permitAll()  // 分享表单
                .antMatchers("/swagger**/**", "/webjars/**", "/v3/**", "/doc.html").permitAll()  // Swagger
                .antMatchers("/**").authenticated() //必须授权才能访问
                .and()
                .formLogin() //使用自带的登录
                .permitAll()
                //登录失败，返回json
                .failureHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    Result<Object> result;
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        result = Result.failed(ResultCode.USER_CREDENTIALS_ERROR, "用户名或密码错误");
                    } else if (ex instanceof DisabledException) {
                        result = Result.failed(ResultCode.USER_ACCOUNT_LOCKED, "账户被禁用");
                    } else {
                        result = Result.failed(ResultCode.COMMON_FAIL, "登录失败!");
                    }
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                })
                //登录成功，返回json
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    UserVO user = new UserVO((UserEntity) authentication.getPrincipal());
                    out.write(objectMapper.writeValueAsString(Result.success(user, "登录成功")));
                    out.flush();
                    out.close();
                })
                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Result.failed(ResultCode.NO_PERMISSION, "权限不足")));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(Result.success(authentication, "退出成功")));
                    out.flush();
                    out.close();
                })
                .deleteCookies("JSESSIONID")  // 登出时删除 JSESSIONID
                .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    /**
     * 配置认证方式等
     * 设置密码加密方式，验证密码的在这里
     *
     * @return authenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
