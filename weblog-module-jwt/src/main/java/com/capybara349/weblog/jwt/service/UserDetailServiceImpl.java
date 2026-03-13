package com.capybara349.weblog.jwt.service;

import com.capybara349.weblog.common.domain.dos.UserDO;
import com.capybara349.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 加载用户信息： 从数据源中加载用户的用户名、密码和角色等信息。
 * 创建 UserDetails 对象： 根据加载的用户信息，创建一个 Spring Security 所需的 UserDetails 对象，包含用户名、密码、角色和权限等。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:34
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        UserDO userDO = userMapper.findByUsername(username);
        // 判断用户是否存在
        if (Objects.isNull(userDO)) {
            throw new UsernameNotFoundException("改用户不存在");
        }
        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities("ADMIN")
                .build();
    }
}
