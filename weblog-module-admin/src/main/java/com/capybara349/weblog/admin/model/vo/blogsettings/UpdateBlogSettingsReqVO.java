package com.capybara349.weblog.admin.model.vo.blogsettings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = " 博客基础信息修改 VO")
public class UpdateBlogSettingsReqVO {

    @NotBlank(message = "博客 LOGO 不能为空")
    private String logo;

    @NotBlank(message = "博客名称不能为空")
    private String name;

    @NotBlank(message = "博客作者不能为空")
    private String author;

    @NotBlank(message = "博客介绍语不能为空")
    private String introduction;

    @NotBlank(message = "博客头像不能为空")
    private String avatar;

    private String githubHomepage;

    private String csdnHomepage;

    private String giteeHomepage;

    private String zhihuHomepage;
}

