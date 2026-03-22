package com.capybara349.weblog.jwt.handler;

import com.capybara349.weblog.common.enums.ResponseCodeEnum;
import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.jwt.exception.CaptchaVerificationFailedException;
import com.capybara349.weblog.jwt.exception.UsernameOrPasswordNullException;
import com.capybara349.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:51
 */
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
            return;
        } else if (exception instanceof BadCredentialsException){
            ResultUtil.fail(response, Response.fail(ResponseCodeEnum.USERNAME_OR_PWD_ERROR));
            return;
        } else if (exception instanceof CaptchaVerificationFailedException) {
            // 行为验证码错误
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        }
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.LOGIN_FAIL));
    }
}
