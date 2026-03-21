package com.capybara349.weblog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 16:49
 */
@Getter
@AllArgsConstructor
public enum CommentStatusEnum {

    // ----------- 通用异常状态码 -----------
    WAIT_EXAMINE(1, "等待审核"),
    NORMAL(2, "正常"),
    EXAMINE_FAILED(3, "审核不通过"),
    ;

    private Integer code;
    private String description;

}
