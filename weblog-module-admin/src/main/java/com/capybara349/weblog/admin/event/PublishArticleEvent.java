package com.capybara349.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 15:59
 */
@Getter
public class PublishArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public PublishArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}
