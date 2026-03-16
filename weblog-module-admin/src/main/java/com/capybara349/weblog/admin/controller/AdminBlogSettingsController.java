package com.capybara349.weblog.admin.controller;

import com.capybara349.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.capybara349.weblog.admin.service.AdminBlogSettingsService;
import com.capybara349.weblog.common.aspect.ApiOperationLog;
import com.capybara349.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:21
 */
@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin 博客设置模块")
public class AdminBlogSettingsController {

    @Autowired
    private AdminBlogSettingsService blogSettingsService;

    @PostMapping("/update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    public Response updateBlogSettings(@RequestBody @Validated UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        return blogSettingsService.updateBlogSettings(updateBlogSettingsReqVO);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取博客设置详情")
    @ApiOperationLog(description = "获取博客设置详情")
    public Response findDetail() {
        return blogSettingsService.findDetail();
    }

}

