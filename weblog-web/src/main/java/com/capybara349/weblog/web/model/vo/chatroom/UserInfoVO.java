package com.capybara349.weblog.web.model.vo.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;
    /**
     * QQ 号（可为空，仅用于 QQ 登录方式）
     */
    private String qq;
}
