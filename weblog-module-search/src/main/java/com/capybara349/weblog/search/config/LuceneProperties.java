package com.capybara349.weblog.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 15:06
 */
@ConfigurationProperties(prefix = "lucene")
@Component
@Data
public class LuceneProperties {
    /**
     * 索引存放的文件夹
     */
    private String indexDir;
}
