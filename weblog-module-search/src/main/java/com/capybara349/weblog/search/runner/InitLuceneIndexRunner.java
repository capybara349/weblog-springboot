package com.capybara349.weblog.search.runner;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.capybara349.weblog.common.constant.Constants;
import com.capybara349.weblog.common.domain.dos.ArticleContentDO;
import com.capybara349.weblog.common.domain.dos.ArticleDO;
import com.capybara349.weblog.common.domain.mapper.ArticleContentMapper;
import com.capybara349.weblog.common.domain.mapper.ArticleMapper;
import com.capybara349.weblog.search.LuceneHelper;
import com.capybara349.weblog.search.config.LuceneProperties;
import com.capybara349.weblog.search.index.ArticleIndex;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Lucene 中常见的一些概念名词
 * 索引（Index）： 索引是 Lucene 中的核心概念，它类似于数据库中的表。在 Lucene 中，索引是由一系列词项（terms）构成的数据结构，每个词项都关联到一个或多个文档。这允许非常快速的搜索，类似于数据库中使用索引进行快速检索的方式；
 * 文档（Document）：文档是 Lucene 中的基本信息单元，可以看作数据库表中的一行。每个文档由一组字段（Field）组成，每个字段包含一个值。文档在索引中存储，并且可以根据这些字段进行搜索；
 * 字段（Field）：字段是文档中的一个属性，它有一个名称和一个值。在搜索和检索中，我们可以使用字段来过滤和排序文档;
 * 分析器（Analyzer）：分析器负责将文本切分成单词，并对这些单词进行标准化处理，以便建立索引和进行搜索。Lucene 提供了各种分析器来处理不同类型的文本数据;
 * 查询（Query）：查询是用于在索引中搜索文档的表达式。Lucene 提供了强大的查询语言，允许我们构建复杂的搜索条件。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.19 14:57
 */
@Component
@Slf4j
public class InitLuceneIndexRunner implements CommandLineRunner {


    @Autowired
    private LuceneProperties luceneProperties;
    @Autowired
    private LuceneHelper luceneHelper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info("==> 开始初始化 Lucene 索引...");

        // 查询所有文章
        List<ArticleDO> articleDOS = articleMapper.selectList(Wrappers.emptyWrapper());

        // 未发布文章，则不创建 lucene 索引
        if (articleDOS.isEmpty()) {
            log.info("==> 未发布任何文章，暂不创建 Lucene 索引...");
            return;
        }

        // 若配置文件中未配置索引存放目录，日志提示错误信息
        if (StringUtils.isBlank(luceneProperties.getIndexDir())) {
            log.error("==> 未指定 Lucene 索引存放位置，需在 application.yml 文件中添加路径配置...");
            return;
        }

        // 文章索引存放目录， 如 ~/logs/lucene-index/article
        String articleIndexDir = luceneProperties.getIndexDir() + File.separator + ArticleIndex.NAME;
        log.info("文章索引存放目录：{}", articleIndexDir);

        List<Document> documents = Lists.newArrayList();
        articleDOS.forEach(articleDO -> {
            Long articleId = articleDO.getId();

            // 查询文章正文
            ArticleContentDO articleContentDO = articleContentMapper.selectByArticleId(articleId);
            // 构建文档
            Document document = new Document();
            // 设置文档字段 Field
            document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_TITLE, articleDO.getTitle(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_COVER, articleDO.getCover(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, articleDO.getSummary(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CONTENT, articleContentDO.getContent(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, Constants.DATE_TIME_FORMATTER.format(articleDO.getCreateTime()), Field.Store.YES));
            documents.add(document);
        });

        // 创建索引
        luceneHelper.createIndex(articleIndexDir, documents);

        log.info("==> 结束初始化 Lucene 索引...");
    }

}
