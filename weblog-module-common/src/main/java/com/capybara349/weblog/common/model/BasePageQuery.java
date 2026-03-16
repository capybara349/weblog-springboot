package com.capybara349.weblog.common.model;

import lombok.Data;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 14:17
 */
@Data
public class BasePageQuery {
    /**
     * 当前页码, 默认第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long size = 10L;
}


