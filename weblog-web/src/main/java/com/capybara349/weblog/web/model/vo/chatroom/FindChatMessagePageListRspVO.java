package com.capybara349.weblog.web.model.vo.chatroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindChatMessagePageListRspVO {

    /**
     * 聊天消息
     */
    private List<ChatMessageVO> messages;

    /**
     * 是否还有下一页
     */
    private Boolean hasMore;

}