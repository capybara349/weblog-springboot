package com.capybara349.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.capybara349.weblog.admin.convert.BlogSettingsConvert;
import com.capybara349.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.capybara349.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.capybara349.weblog.admin.service.AdminBlogSettingsService;
import com.capybara349.weblog.common.domain.dos.BlogSettingsDO;
import com.capybara349.weblog.common.domain.mapper.BlogSettingsMapper;
import com.capybara349.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:20
 */
@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);

        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    /**
     * 获取博客设置详情
     *
     * @return
     */
    @Override
    public Response findDetail() {
        // 查询 ID 为 1 的记录
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);

        // DO 转 VO
        FindBlogSettingsRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(vo);
    }
}

