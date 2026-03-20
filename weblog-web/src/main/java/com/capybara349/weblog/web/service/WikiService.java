package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.wiki.FindWikiArticlePreNextReqVO;
import com.capybara349.weblog.web.model.vo.wiki.FindWikiCatalogListReqVO;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 15:19
 */
public interface WikiService {
    /**
     * 获取知识库
     * @return
     */
    Response findWikiList();
    /**
     * 获取知识库目录
     * @param findWikiCatalogListReqVO
     * @return
     */
    Response findWikiCatalogList(FindWikiCatalogListReqVO findWikiCatalogListReqVO);
    /**
     * 获取上下页
     * @param findWikiArticlePreNextReqVO
     * @return
     */
    Response findArticlePreNext(FindWikiArticlePreNextReqVO findWikiArticlePreNextReqVO);

}
