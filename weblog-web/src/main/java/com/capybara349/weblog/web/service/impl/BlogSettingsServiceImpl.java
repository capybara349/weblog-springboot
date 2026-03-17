package com.capybara349.weblog.web.service.impl;

import com.capybara349.weblog.common.domain.dos.BlogSettingsDO;
import com.capybara349.weblog.common.domain.mapper.BlogSettingsMapper;
import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.convert.BlogSettingsConvert;
import com.capybara349.weblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import com.capybara349.weblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.17 15:59
 */
@Service
@Slf4j
public class BlogSettingsServiceImpl implements BlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    /**
     * 获取博客设置信息
     *
     * @return
     */
    @Override
    public Response findDetail() {
        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        // DO 转 VO
        FindBlogSettingsDetailRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(vo);
    }
}

