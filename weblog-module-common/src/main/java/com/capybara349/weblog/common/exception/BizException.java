package com.capybara349.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 17:02
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
