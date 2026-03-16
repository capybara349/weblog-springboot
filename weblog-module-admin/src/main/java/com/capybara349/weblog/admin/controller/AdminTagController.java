package com.capybara349.weblog.admin.controller;

import com.capybara349.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.capybara349.weblog.admin.model.vo.tag.AddTagReqVO;
import com.capybara349.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.capybara349.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.capybara349.weblog.admin.model.vo.tag.SearchTagsReqVO;
import com.capybara349.weblog.admin.service.AdminTagService;
import com.capybara349.weblog.common.aspect.ApiOperationLog;
import com.capybara349.weblog.common.utils.PageResponse;
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
 * &#064;date 2026.03.16 14:11
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 标签模块")
public class AdminTagController {
    @Autowired
    private AdminTagService tagService;

    @PostMapping("/tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addCategory(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return tagService.addTag(addTagReqVO);
    }

    @PostMapping("/tag/list")
    @ApiOperation(value = "标签分页数据获取")
    @ApiOperationLog(description = "标签分页数据获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return tagService.findTagPageList(findTagPageListReqVO);
    }
    @PostMapping("/tag/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return tagService.deleteTag(deleteTagReqVO);
    }

    @PostMapping("/tag/search")
    @ApiOperation(value = "标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTags(@RequestBody @Validated SearchTagsReqVO searchTagsReqVO) {
        return tagService.searchTags(searchTagsReqVO);
    }


}
