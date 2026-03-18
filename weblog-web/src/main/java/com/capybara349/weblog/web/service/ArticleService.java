package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.article.FindArticleDetailReqVO;
import com.capybara349.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.17 15:48
 */
public interface ArticleService {
    /**
     * 获取首页文章分页数据
     * @param findIndexArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    /**
     * 获取文章详情
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);

}