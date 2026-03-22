package com.capybara349.weblog.web.model.vo.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineUserVO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否在线
     */
    private Boolean online;
}
