package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.capybara349.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.capybara349.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.capybara349.weblog.common.utils.PageResponse;
import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 14:06
 */
public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);
    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
    /**
     * 获取文章分类的 Select 列表数据
     * @return
     */
    Response findCategorySelectList();
}
