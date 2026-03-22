package com.capybara349.weblog.jwt.config;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import com.capybara349.weblog.jwt.filter.JwtAuthenticationFilter;
import com.capybara349.weblog.jwt.handler.RestAuthenticationFailureHandler;
import com.capybara349.weblog.jwt.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置类，用于配置 JWT（JSON Web Token）的身份验证机制。
 * 它继承了 Spring Security 的 SecurityConfigurerAdapter 类，用于在 Spring Security 配置中添加自定义的认证过滤器和提供者。
 * 通过重写 configure() 方法，我们将之前写好过滤器、认证成功、失败处理器，以及加密算法整合到了 httpSecurity 中。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:54
 */
@Configuration
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ImageCaptchaApplication imageCaptchaApplication;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        // 自定义的用于 JWT 身份验证的过滤器
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        // 设置验证码校验器
        filter.setImageCaptchaApplication(imageCaptchaApplication);

        // 设置登录认证对应的处理类（成功处理、失败处理）
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);

        // 直接使用 DaoAuthenticationProvider, 它是 Spring Security 提供的默认的身份验证提供者之一
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置 userDetailService，用于获取用户的详细信息
        provider.setUserDetailsService(userDetailsService);
        // 设置加密算法
        provider.setPasswordEncoder(passwordEncoder);
        builder.authenticationProvider(provider);
        // 将这个过滤器添加到 UsernamePasswordAuthenticationFilter 之前执行
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
