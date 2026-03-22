package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.chatroom.FindChatMessagePageListReqVO;
import com.capybara349.weblog.web.model.vo.chatroom.FindChatMessagePageListRspVO;
import com.capybara349.weblog.web.model.vo.chatroom.OnlineUserVO;

import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:14
 */
public interface ChatRoomService {

    /**
     * 获取历史消息
     *
     * @param findChatMessagePageListReqVO
     * @return
     */
    Response<FindChatMessagePageListRspVO> findHistoryMessages(FindChatMessagePageListReqVO findChatMessagePageListReqVO);
    /**
     * 获取所有在线用户
     * @return
     */
    Response<List<OnlineUserVO>> findOnlineUsers();
}
