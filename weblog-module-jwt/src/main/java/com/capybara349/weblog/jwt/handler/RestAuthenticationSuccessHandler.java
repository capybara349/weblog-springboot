package com.capybara349.weblog.jwt.handler;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.jwt.modle.LoginRspVO;
import com.capybara349.weblog.jwt.utils.JwtTokenHelper;
import com.capybara349.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在 Spring Security 中，AuthenticationFailureHandler 和 AuthenticationSuccessHandler 是用于处理身份验证失败和成功的接口。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:42
 */
@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，这里是获取用户的用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 Token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);

        // 返回 Token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();

        ResultUtil.ok(response, Response.success(loginRspVO));
    }
}
