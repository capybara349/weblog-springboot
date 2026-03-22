package com.capybara349.weblog.web.model.vo.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebSocketChatMessageVO {


    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private String time;

    /**
     * 在线人数
     */
    private Integer onlineCount;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 返回会话 ID
     */
    private String sessionId;
}
