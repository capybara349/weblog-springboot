package com.capybara349.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 10:41
 */
@Getter
public class UpdateCommentEvent extends ApplicationEvent {

    /**
     * 评论 ID
     */
    private Long commentId;

    public UpdateCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}
