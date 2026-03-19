package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.search.SearchArticlePageListReqVO;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 15:27
 */
public interface SearchService {
    /**
     * 关键词分页搜索
     * @param searchArticlePageListReqVO
     * @return
     */
    Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO);
}
