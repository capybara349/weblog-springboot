package com.capybara349.weblog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 14:30
 */
@Getter
@AllArgsConstructor
public enum WikiCatalogLevelEnum {

    // 一级目录
    ONE(1),
    // 二级目录
    TWO(2);

    private Integer value;

}
