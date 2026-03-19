package com.capybara349.weblog.admin.runner;

import com.capybara349.weblog.admin.service.AdminStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 17:11
 */
@Component
@Slf4j
public class StatisticsCategoryArticleTotalRunner implements CommandLineRunner {

    @Autowired
    private AdminStatisticsService statisticsService;

    @Override
    @Async("threadPoolTaskExecutor")
    public void run(String... args) throws Exception {
        log.info("==> 开始统计各分类下文章数量...");
        statisticsService.statisticsCategoryArticleTotal();
        log.info("==> 结束统计各分类下文章数量...");
    }
}
