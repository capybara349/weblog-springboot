package com.capybara349.weblog.web.service.impl;

import com.capybara349.weblog.common.domain.dos.ChatMessageDO;
import com.capybara349.weblog.common.domain.mapper.ChatMessageMapper;
import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.enums.ChatRoomMessageTypeEnum;
import com.capybara349.weblog.web.model.vo.chatroom.ChatMessageVO;
import com.capybara349.weblog.web.model.vo.chatroom.FindChatMessagePageListReqVO;
import com.capybara349.weblog.web.model.vo.chatroom.FindChatMessagePageListRspVO;
import com.capybara349.weblog.web.model.vo.chatroom.OnlineUserVO;
import com.capybara349.weblog.web.service.ChatRoomService;
import com.capybara349.weblog.web.utils.DateTimeFormatUtil;
import com.capybara349.weblog.web.websocket.ChatWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:14
 */
@Service
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    /**
     * 获取历史消息
     * 首先，定义 pageSize 变量，即每页展示的消息条数为 10 条。
     * 获取请求参数中的游标消息 ID（lastId），这个 ID 用于分页查询。
     * 根据 lastId 是否为空来决定查询方式：
     * 如果 lastId 为空，则查询最近的消息（第一页），调用 selectRecentMessages 方法，传入 pageSize。
     * 如果 lastId 不为空，则查询比 lastId 更早的消息（上一页），调用 selectMessagesBefore 方法，传入 lastId 和pageSize。
     * 将查询到的消息列表（DO 实体类集合）转换为 VO 实体类集合。
     * 如果查询到的消息集合不为空，则进行以下操作：
     * 将消息集合倒序（因为查询出来的消息是按时间倒序的，我们需要正序展示）。
     * 遍历消息集合，将每个 DO 对象转换为 VO 对象。
     * 构建响应 VO 对象，包含消息 VO 集合和是否有更多消息的标志（hasMore）。判断是否有更多消息的依据是：当前返回的消息数量，是否大于等于 pageSize。
     * 最后，返回成功的响应。
     * @param findChatMessagePageListReqVO
     * @return
     */
    @Override
    public Response<FindChatMessagePageListRspVO> findHistoryMessages(FindChatMessagePageListReqVO findChatMessagePageListReqVO) {
        // 每页展示 10 条消息
        int pageSize = 10;
        // 游标消息 ID
        Long lastId = findChatMessagePageListReqVO.getLastId();
        // 当前用户的 sessionId
        String sessionId = findChatMessagePageListReqVO.getSessionId();

        // 查询指定页码记录
        List<ChatMessageDO> messages = Objects.isNull(lastId)
                ? chatMessageMapper.selectRecentMessages(pageSize) // 如果 lastId 为 null, 说明查询的是第一页消息
                : chatMessageMapper.selectMessagesBefore(lastId, pageSize); // 若 lastId 不为 null, 则执行分页查询

        // DO 实体类转 VO 实体类
        FindChatMessagePageListRspVO vo = null;

        if (!CollectionUtils.isEmpty(messages)) {
            // 倒序变正序
            Collections.reverse(messages);

            List<ChatMessageVO> voList = messages.stream().map(chatMessageDO -> {
                        // 判断是否是当前用户发送的消息
                        // 如果 DO 的 qq 不为空，并且入参提交的 sessionId 和 qq 号一致，则是自己发送的消息
                        boolean isSelf = StringUtils.isNotBlank(chatMessageDO.getQq())
                                && StringUtils.isNotBlank(sessionId)
                                && Objects.equals(sessionId, chatMessageDO.getQq());

                return ChatMessageVO.builder()
                        .id(chatMessageDO.getId())
                        .type(ChatRoomMessageTypeEnum.CHAT.getCode())
                        .nickname(chatMessageDO.getNickname())
                        .avatar(chatMessageDO.getAvatar())
                        .content(chatMessageDO.getContent())
                        .time(DateTimeFormatUtil.formatChatTime(chatMessageDO.getCreateTime()))
                        .isSelf(isSelf)
                        .build();
                    }
            ).collect(Collectors.toList());

            vo = FindChatMessagePageListRspVO.builder()
                    .messages(voList)
                    .hasMore(messages.size() >= pageSize) // 是否还有下一页
                    .build();
        }

        return Response.success(vo);
    }
    @Override
    public Response<List<OnlineUserVO>> findOnlineUsers() {
        // 获取所有在线用户
        List<OnlineUserVO> onlineUsers = ChatWebSocketServer.getOnlineUsers();
        return Response.success(CollectionUtils.isEmpty(onlineUsers) ? null : onlineUsers);
    }
}
