package com.capybara349.weblog.web.model.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 17:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCommentListReqVO {

    @NotBlank(message = "路由地址不能为空")
    private String routerUrl;

}