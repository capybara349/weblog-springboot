package com.capybara349.weblog.common.enums;

import com.capybara349.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 16:58
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    PARAM_NOT_VALID("10001", "参数错误"),
    LOGIN_FAIL("20001", "登录失败"),
    USERNAME_OR_PWD_ERROR("20002", "用户名或密码错误"),
    UNAUTHORIZED("20003", "无访问权限，请先登陆！"),
    FORBIDDEN("20004", "演示账号仅支持查询操作！"),
    USERNAME_NOT_FOUND("20003", "该用户不存在"),
    ;

    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;
}
