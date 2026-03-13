package com.capybara349.weblog.jwt.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.13 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {
    /**
     * Token 值
     */
    private String token;
}
