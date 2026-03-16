package com.capybara349.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加标签 VO")
public class AddTagReqVO {

    @NotEmpty(message = "标签不能为空")
    private List<String> tags;
}
