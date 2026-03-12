package com.capybara349.weblogweb.model;

import lombok.Data;

import javax.validation.constraints.*;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.12 13:58
 */
@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotNull(message = "性别不能为空")
    private Integer sex;
    @Min(value = 18, message = "年龄必须大于等于 18")
    @Max(value = 100, message = "年龄必须小于等于 100")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
