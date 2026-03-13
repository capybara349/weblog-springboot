package com.capybara349.weblog.admin.config;

import com.capybara349.weblog.jwt.config.JwtAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 过覆盖方法 类可以配置认证、授权规则、自定义登录页面、注销等。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:04
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    /**
     * 在 configure() 方法中，首先禁用了 CSRF（Cross-Site Request Forgery）攻击防护。
     * 在前后端分离的情况下，通常不需要启用 CSRF 防护。同时，还禁用了表单登录，并应用了 JWT 相关的配置类 JwtAuthenticationSecurityConfig。
     * 最后，配置会话管理这块，将会话策略设置为无状态（STATELESS），适用于前后端分离的情况，无需创建会话。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .apply(jwtAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .mvcMatchers("admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
