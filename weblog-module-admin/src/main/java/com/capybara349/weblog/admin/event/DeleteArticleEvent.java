package com.capybara349.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 16:04
 */
@Getter
public class DeleteArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public DeleteArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}
