package com.capybara349.weblog.web.model.vo.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineUsersMessageVO {

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 在线用户列表
     */
    private List<OnlineUserVO> users;

    /**
     * 在线人数
     */
    private Integer onlineCount;
}
