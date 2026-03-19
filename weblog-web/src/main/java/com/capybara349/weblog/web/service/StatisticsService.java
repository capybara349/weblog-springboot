package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 14:11
 */
public interface StatisticsService {
    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     * @return
     */
    Response findInfo();
}