package com.capybara349.weblog.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 14:55
 */
@Getter
@AllArgsConstructor
public enum ChatRoomMessageTypeEnum {

    SYSTEM(0, "系统消息"),
    CHAT(1, "聊天消息"),
    ONLINE_USERS(2, "在线用户列表消息"),
    INIT(3, "会话初始化消息")// 用于返回 sessionId
    ;
    private Integer code;
    private String description;

}
