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
public enum ArticleTypeEnum {

    NORMAL(1, "普通"),
    WIKI(2, "收录于知识库");

    private Integer value;
    private String description;

}
