package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:20
 */
public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     *
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客设置详情
     *
     * @return
     */
    Response findDetail();
}

