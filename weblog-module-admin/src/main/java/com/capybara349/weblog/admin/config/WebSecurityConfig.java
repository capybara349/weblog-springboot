package com.capybara349.weblog.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 过覆盖方法 类可以配置认证、授权规则、自定义登录页面、注销等。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:04
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 认证所有以 /admin 为前缀的 URL 资源;其他都需要放行，无需认证;使用表单登录;使用 HTTP Basic 认证。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("admin/**").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().and()
                .httpBasic();
    }
}
