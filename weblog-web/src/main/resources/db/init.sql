/*
 Navicat Premium Dump SQL

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80045 (8.0.45)
 Source Host           : localhost:3307
 Source Schema         : weblog

 Target Server Type    : MySQL
 Target Server Version : 80045 (8.0.45)
 File Encoding         : 65001

 Date: 23/03/2026 01:36:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
                             `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
                             `title` varchar(120) NOT NULL DEFAULT '' COMMENT '文章标题',
                             `cover` varchar(120) NOT NULL DEFAULT '' COMMENT '文章封面',
                             `summary` varchar(160) DEFAULT '' COMMENT '文章摘要',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                             `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
                             `read_num` int unsigned NOT NULL DEFAULT '1' COMMENT '被阅读次数',
                             `weight` int unsigned NOT NULL DEFAULT '0' COMMENT '文章权重，用于是否置顶（0: 未置顶；>0: 参与置顶，权重值越高越靠前）',
                             `type` tinyint NOT NULL DEFAULT '1' COMMENT '文章类型 - 1：普通文章，2：收录于知识库',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章表';

-- ----------------------------
-- Table structure for t_article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_category_rel`;
CREATE TABLE `t_article_category_rel` (
                                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                          `article_id` bigint unsigned NOT NULL COMMENT '文章id',
                                          `category_id` bigint unsigned NOT NULL COMMENT '分类id',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          UNIQUE KEY `uni_article_id` (`article_id`) USING BTREE,
                                          KEY `idx_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章所属分类关联表';

-- ----------------------------
-- Table structure for t_article_content
-- ----------------------------
DROP TABLE IF EXISTS `t_article_content`;
CREATE TABLE `t_article_content` (
                                     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
                                     `article_id` bigint NOT NULL COMMENT '文章id',
                                     `content` text COMMENT '教程正文',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     KEY `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章内容表';

-- ----------------------------
-- Table structure for t_article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag_rel`;
CREATE TABLE `t_article_tag_rel` (
                                     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `article_id` bigint unsigned NOT NULL COMMENT '文章id',
                                     `tag_id` bigint unsigned NOT NULL COMMENT '标签id',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     KEY `idx_article_id` (`article_id`) USING BTREE,
                                     KEY `idx_tag_id` (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章对应标签关联表';

-- ----------------------------
-- Table structure for t_blog_settings
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_settings`;
CREATE TABLE `t_blog_settings` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `logo` varchar(120) NOT NULL DEFAULT '' COMMENT '博客Logo',
                                   `name` varchar(60) NOT NULL DEFAULT '' COMMENT '博客名称',
                                   `author` varchar(20) NOT NULL DEFAULT '' COMMENT '作者名',
                                   `introduction` varchar(120) NOT NULL DEFAULT '' COMMENT '介绍语',
                                   `avatar` varchar(120) NOT NULL DEFAULT '' COMMENT '作者头像',
                                   `github_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
                                   `csdn_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
                                   `gitee_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
                                   `zhihu_homepage` varchar(60) NOT NULL DEFAULT '' COMMENT '知乎主页访问地址',
                                   `mail` varchar(60) DEFAULT '' COMMENT '博主邮箱地址',
                                   `is_comment_sensi_word_open` tinyint NOT NULL DEFAULT '1' COMMENT '是否开启评论敏感词过滤, 0:不开启；1：开启',
                                   `is_comment_examine_open` tinyint NOT NULL DEFAULT '0' COMMENT '是否开启评论审核, 0: 未开启；1：开启',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='博客设置表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
                              `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '分类id',
                              `name` varchar(60) NOT NULL DEFAULT '' COMMENT '分类名称',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                              `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                              `articles_total` int NOT NULL DEFAULT '0' COMMENT '此分类下文章总数',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `uk_name` (`name`) USING BTREE,
                              KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章分类表';

-- ----------------------------
-- Table structure for t_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `nickname` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送者昵称',
                                  `avatar` varchar(160) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发送者头像',
                                  `content` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
                                  `qq` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发送者QQ号（可为空）',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='聊天室消息表';

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
                             `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `content` varchar(120) NOT NULL DEFAULT '' COMMENT '评论内容',
                             `avatar` varchar(160) DEFAULT NULL COMMENT '头像',
                             `nickname` varchar(60) NOT NULL DEFAULT '' COMMENT '昵称',
                             `mail` varchar(60) NOT NULL DEFAULT '' COMMENT '邮箱',
                             `website` varchar(60) DEFAULT NULL COMMENT '网站地址',
                             `router_url` varchar(60) NOT NULL DEFAULT '' COMMENT '评论所属的路由',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                             `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
                             `reply_comment_id` bigint unsigned DEFAULT NULL COMMENT '回复的评论 ID',
                             `parent_comment_id` bigint unsigned DEFAULT NULL COMMENT '父评论 ID',
                             `reason` varchar(300) DEFAULT '' COMMENT '原因描述',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '1: 待审核；2：正常；3：审核未通过;',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_router_url` (`router_url`) USING BTREE,
                             KEY `idx_create_time` (`create_time`) USING BTREE,
                             KEY `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
                             KEY `idx_parent_comment_id` (`parent_comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Table structure for t_statistics_article_pv
-- ----------------------------
DROP TABLE IF EXISTS `t_statistics_article_pv`;
CREATE TABLE `t_statistics_article_pv` (
                                           `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                           `pv_date` date NOT NULL COMMENT '被统计的日期',
                                           `pv_count` bigint unsigned NOT NULL COMMENT 'pv访问量',
                                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           UNIQUE KEY `uk_pv_date` (`pv_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='统计表 - 文章 PV (访问量)';

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
                         `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
                         `name` varchar(60) NOT NULL DEFAULT '' COMMENT '标签名称',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                         `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                         `articles_total` int NOT NULL DEFAULT '0' COMMENT '此标签下文章总数',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE KEY `uk_name` (`name`) USING BTREE,
                         KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `username` varchar(60) NOT NULL COMMENT '用户名',
                          `password` varchar(60) NOT NULL COMMENT '密码',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：0：未删除 1：已删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `username` varchar(60) NOT NULL COMMENT '用户名',
                               `role` varchar(60) NOT NULL COMMENT '角色',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Table structure for t_wiki
-- ----------------------------
DROP TABLE IF EXISTS `t_wiki`;
CREATE TABLE `t_wiki` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `title` varchar(120) NOT NULL DEFAULT '' COMMENT '标题',
                          `cover` varchar(120) NOT NULL DEFAULT '' COMMENT '封面',
                          `summary` varchar(160) DEFAULT '' COMMENT '摘要',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
                          `weight` int unsigned NOT NULL DEFAULT '0' COMMENT '权重，用于是否置顶（0: 未置顶；>0: 参与置顶，权重值越高越靠前）',
                          `is_publish` tinyint NOT NULL DEFAULT '1' COMMENT '是否发布：0：未发布 1：已发布',
                          PRIMARY KEY (`id`) USING BTREE,
                          KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='知识库表';

-- ----------------------------
-- Table structure for t_wiki_catalog
-- ----------------------------
DROP TABLE IF EXISTS `t_wiki_catalog`;
CREATE TABLE `t_wiki_catalog` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `wiki_id` bigint unsigned NOT NULL COMMENT '知识库id',
                                  `article_id` bigint unsigned DEFAULT NULL COMMENT '文章id',
                                  `title` text NOT NULL COMMENT '标题',
                                  `level` tinyint NOT NULL DEFAULT '1' COMMENT '目录层级',
                                  `parent_id` bigint unsigned DEFAULT NULL COMMENT '父目录id',
                                  `sort` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '排序',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                                  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE KEY `uk_article_id` (`article_id`) USING BTREE,
                                  KEY `idx_sort` (`sort`) USING BTREE,
                                  KEY `idx_wiki_id` (`wiki_id`) USING BTREE,
                                  KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='知识库目录表';

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table data for t_user
-- ----------------------------
INSERT INTO `weblog`.`t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 'capybara349', '$2a$10$hfy5ClgIo701GbCGtzSPneY.OgWncA3gdqqawgqABT2o3mjM4qkbO', '2026-03-13 10:47:06', '2026-03-16 16:49:29', 0);
INSERT INTO `weblog`.`t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (2, 'test', '$2a$10$SNUb9no2H1lV/lyUK2hiPO2KKpeVu2g6alUQ5gkzace8pwFEiP.fS', '2026-03-14 16:14:15', '2026-03-14 16:14:15', 0);
-- ----------------------------
-- Table data for t_user_role
-- ----------------------------
INSERT INTO `weblog`.`t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (1, 'capybara349', 'ROLE_ADMIN', '2026-03-15 23:59:36');
INSERT INTO `weblog`.`t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (2, 'test', 'ROLE_VISITOR', '2026-03-14 16:15:40');
-- ----------------------------
-- Table data for t_blog_settings
-- ----------------------------
INSERT INTO `weblog`.`t_blog_settings` (`id`, `logo`, `name`, `author`, `introduction`, `avatar`, `github_homepage`, `csdn_homepage`, `gitee_homepage`, `zhihu_homepage`, `mail`, `is_comment_sensi_word_open`, `is_comment_examine_open`) VALUES (1, 'https://avatars.githubusercontent.com/u/52271563?s=400&u=692660f2856e8af132058a1de0b256c446f6a0d0&v=4', 'capybara blog', 'capybara349', '嘻嘻哈哈', 'https://avatars.githubusercontent.com/u/52271563?s=400&u=692660f2856e8af132058a1de0b256c446f6a0d0&v=4', 'https://capybara349.github.io', '', '', '', '', 1, 1);

