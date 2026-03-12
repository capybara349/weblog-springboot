package com.capybara349.weblogweb.controller;

import com.capybara349.weblogmodulecommon.aspect.ApiOperationLog;
import com.capybara349.weblogmodulecommon.utils.JsonUtil;
import com.capybara349.weblogmodulecommon.utils.Response;
import com.capybara349.weblogweb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 13:58
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 主动定义一个运行时异常，分母不能为零
//        int i = 1 / 0;
//        return Response.success();
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
//        throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return Response.fail(errorMsg);
        }
        return Response.success();
    }

    @PostMapping("/test1")
    @ApiOperationLog(description = "测试接口1")
    public Response test1(@RequestBody @Validated User user) {
        return Response.success();
    }

    @PostMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response test2(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());
        return Response.success(user);
    }
}
