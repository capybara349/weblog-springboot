package com.capybara349.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.22 16:46
 */
public class CaptchaVerificationFailedException extends AuthenticationException {
    public CaptchaVerificationFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaVerificationFailedException(String msg) {
        super(msg);
    }
}
