package com.capybara349.weblog.common.exception;

import com.capybara349.weblog.common.enums.ResponseCodeEnum;
import com.capybara349.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 17:07
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Response.fail(e);
    }

    /**
     * 其他异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 参数校验的异常捕获，记得去掉接口方法参数中的 BindingResult
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // 参数错误异常码
        String errorCode = ResponseCodeEnum.PRODUCT_NOT_FOUND.getErrorCode();

        // 获取 BindingResult
        BindingResult bindingResult = e.getBindingResult();

        // 获取校验不通过的字段，并组合错误信息，格式为： email 邮箱格式不正确, 当前值: '123124qq.com';
        StringBuilder stringBuilder = new StringBuilder();
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(fieldErrors -> {
            fieldErrors.forEach(fieldError ->
                    stringBuilder.append(fieldError.getField())
                    .append(" ")
                    .append(fieldError.getDefaultMessage())
                    .append(", 当前值: ")
                    .append(fieldError.getRejectedValue())
                    .append("';"));
        });

        // 错误信息
        String errorMessage = stringBuilder.toString();

        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);

        return Response.fail(errorCode, errorMessage);
    }
}
