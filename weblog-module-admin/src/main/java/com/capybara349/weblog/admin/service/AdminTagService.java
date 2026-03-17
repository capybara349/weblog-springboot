package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.admin.model.vo.tag.AddTagReqVO;
import com.capybara349.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.capybara349.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.capybara349.weblog.admin.model.vo.tag.SearchTagsReqVO;
import com.capybara349.weblog.common.utils.PageResponse;
import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 14:06
 */
public interface AdminTagService {
    /**
     * 添加标签
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页查询
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * 根据标签关键词模糊查询
     *
     * @param searchTagsReqVO
     * @return
     */
    Response searchTags(SearchTagsReqVO searchTagsReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();
}
