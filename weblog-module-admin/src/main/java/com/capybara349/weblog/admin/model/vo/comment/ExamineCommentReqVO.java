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
 * &#064;date 2026.03.21 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "评论审核 VO")
public class ExamineCommentReqVO {

    @NotNull(message = "评论 ID 不能为空")
    private Long id;

    @NotNull(message = "评论状态不能为空")
    private Integer status;

    /**
     * 原因
     */
    private String reason;
}
