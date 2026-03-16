package com.capybara349.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签模糊查询 VO")
public class SearchTagsReqVO {

    @NotBlank(message = "标签查询关键词不能为空")
    private String key;

}
