package com.capybara349.weblog.admin.model.vo.comment;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 10:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除评论 VO")
public class DeleteCommentReqVO {

    @NotNull(message = "评论 ID 不能为空")
    private Long id;
}