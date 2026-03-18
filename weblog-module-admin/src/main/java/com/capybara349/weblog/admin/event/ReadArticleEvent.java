package com.capybara349.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.18 14:18
 */
@Getter
public class ReadArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public ReadArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}
