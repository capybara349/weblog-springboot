package com.capybara349.weblog.web.model.vo.archive;

import com.capybara349.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.17 17:16
 */
@Data
@Builder
@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
