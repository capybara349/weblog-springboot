package com.capybara349.weblog.web.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 17:20
 */
@Getter
public class PublishCommentEvent extends ApplicationEvent {

    /**
     * 评论 ID
     */
    private Long commentId;

    public PublishCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}
