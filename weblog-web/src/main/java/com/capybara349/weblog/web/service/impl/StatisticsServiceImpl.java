package com.capybara349.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.capybara349.weblog.common.domain.dos.ArticleDO;
import com.capybara349.weblog.common.domain.mapper.ArticleMapper;
import com.capybara349.weblog.common.domain.mapper.CategoryMapper;
import com.capybara349.weblog.common.domain.mapper.TagMapper;
import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.statistics.FindStatisticsInfoRspVO;
import com.capybara349.weblog.web.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 14:12
 */
@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;

    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     *
     * @return
     */
    @Override
    public Response findInfo() {
        // 文章总数
        Long articleTotalCount = articleMapper.selectCount(Wrappers.emptyWrapper());
        // 分类总数
        Long categoryTotalCount = categoryMapper.selectCount(Wrappers.emptyWrapper());
        // 标签总数
        Long tagTotalCount = tagMapper.selectCount(Wrappers.emptyWrapper());
        // 总浏览量
        List<ArticleDO> articleDOS = articleMapper.selectAllReadNum();
        Long pvTotalCount = 0L;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            pvTotalCount = articleDOS.stream().mapToLong(ArticleDO::getReadNum).sum();
        }

        FindStatisticsInfoRspVO vo = FindStatisticsInfoRspVO.builder()
                .articleTotalCount(articleTotalCount)
                .categoryTotalCount(categoryTotalCount)
                .tagTotalCount(tagTotalCount)
                .pvTotalCount(pvTotalCount)
                .build();

        return Response.success(vo);
    }

}

