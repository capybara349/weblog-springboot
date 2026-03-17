package com.capybara349.weblog.web.model.vo.article;

import com.capybara349.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.17 15:44
 */
@Data
@Builder
@ApiModel(value = "首页查询文章分页 VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}

