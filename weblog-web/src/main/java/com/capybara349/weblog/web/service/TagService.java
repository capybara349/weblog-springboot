package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.17 15:56
 */
public interface TagService {
    /**
     * 获取标签列表
     * @return
     */
    Response findTagList();
}
