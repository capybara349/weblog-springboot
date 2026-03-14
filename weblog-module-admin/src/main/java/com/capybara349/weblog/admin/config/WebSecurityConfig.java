package com.capybara349.weblog.admin.config;

import com.capybara349.weblog.jwt.config.JwtAuthenticationSecurityConfig;
import com.capybara349.weblog.jwt.filter.TokenAuthenticationFilter;
import com.capybara349.weblog.jwt.handler.RestAccessDeniedHandler;
import com.capybara349.weblog.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 过覆盖方法 类可以配置认证、授权规则、自定义登录页面、注销等。
 *
 * @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
 * 这是一个 Spring Security 注解，用于启用方法级别的安全性设置。prePostEnabled = true 表示启用 @PreAuthorize 和 @PostAuthorize 注解，securedEnabled = true 表示启用 @Secured 注解。
 * 这意味着您可以在方法级别使用这些注解来定义访问控制规则。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:04
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private RestAccessDeniedHandler deniedHandler;
    /**
     * 在 configure() 方法中，首先禁用了 CSRF（Cross-Site Request Forgery）攻击防护。
     * 在前后端分离的情况下，通常不需要启用 CSRF 防护。同时，还禁用了表单登录，并应用了 JWT 相关的配置类 JwtAuthenticationSecurityConfig。
     * 最后，配置会话管理这块，将会话策略设置为无状态（STATELESS），适用于前后端分离的情况，无需创建会话。
     * .httpBasic().authenticationEntryPoint(authEntryPoint) : 处理用户未登录访问受保护的资源的情况；
     * .exceptionHandling().accessDeniedHandler(deniedHandler) : 处理登录成功后访问受保护的资源，但是权限不够的情况;
     * .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) : 将 Token 校验过滤器添加到用户认证过滤器之前;
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().disable()
                .apply(jwtAuthenticationSecurityConfig)
                .and().authorizeRequests().mvcMatchers("admin/**").authenticated().anyRequest().permitAll()
                .and().httpBasic().authenticationEntryPoint(authEntryPoint) // 处理用户未登录访问受保护的资源的情况
                .and().exceptionHandling().accessDeniedHandler(deniedHandler) // 处理登录成功后访问受保护的资源，但是权限不够的情况
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 将 Token 校验过滤器添加到用户认证过滤器之前
        ;

    }
    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}
