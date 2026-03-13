package com.capybara349.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 需继承自 AuthenticationException，只有该类型异常，才能被后续自定义的认证失败处理器捕获到。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:39
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
