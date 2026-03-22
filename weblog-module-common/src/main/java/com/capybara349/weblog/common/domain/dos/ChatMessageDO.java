package com.capybara349.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 15:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_chat_message")
public class ChatMessageDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String avatar;

    private String content;

    private LocalDateTime createTime;
    private String qq;
}
