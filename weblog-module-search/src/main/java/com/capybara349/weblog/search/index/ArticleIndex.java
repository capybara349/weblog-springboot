package com.capybara349.weblog.search.index;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 15:07
 */
public interface ArticleIndex {
    /**
     * 索引名称
     */
    String NAME = "article";

    // --------------------- 文档字段 ---------------------
    String COLUMN_ID = "id";

    String COLUMN_TITLE = "title";

    String COLUMN_COVER = "cover";

    String COLUMN_SUMMARY = "summary";

    String COLUMN_CONTENT = "content";

    String COLUMN_CREATE_TIME = "createTime";
}
