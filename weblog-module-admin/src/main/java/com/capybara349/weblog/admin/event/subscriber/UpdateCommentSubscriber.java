package com.capybara349.weblog.admin.event.subscriber;

import com.capybara349.weblog.admin.event.UpdateCommentEvent;
import com.capybara349.weblog.common.domain.dos.BlogSettingsDO;
import com.capybara349.weblog.common.domain.dos.CommentDO;
import com.capybara349.weblog.common.domain.mapper.BlogSettingsMapper;
import com.capybara349.weblog.common.domain.mapper.CommentMapper;
import com.capybara349.weblog.common.enums.CommentStatusEnum;
import com.capybara349.weblog.common.mail.MailHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 当订阅到评论更新事件后，从事件对象中获取到评论 ID, 根据 ID 获取到评论数据;
 * 接着，查询博客设置信息，获取博客名称；
 * 判断评论状态， 若审核不通过，并且邮箱地址有填写的话，邮件通知发评论的用户：你的评论未被博主审核通过，原因是什么；
 * 如果是审核通过，通知发评论的用户，你的评论已经被博主审核通过；同时，还需要邮件通知被回复的用户，告知评论被谁回复了，内容是什么；
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 10:41
 */
@Component
@Slf4j
public class UpdateCommentSubscriber implements ApplicationListener<UpdateCommentEvent> {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Autowired
    private MailHelper mailHelper;
    @Value("${domain.url}")
    private String domain;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(UpdateCommentEvent event) {
        // 在这里处理收到的事件，可以是任何逻辑操作
        Long commentId = event.getCommentId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 评论发布事件消费成功，commentId: {}", commentId);

        CommentDO commentDO = commentMapper.selectById(commentId);
        Long replyCommentId = commentDO.getReplyCommentId();
        String nickname = commentDO.getNickname();
        String content = commentDO.getContent();
        Integer status = commentDO.getStatus();
        String mail = commentDO.getMail();
        String routerUrl = commentDO.getRouterUrl();


        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        String blogName = blogSettingsDO.getName();


        // 判断评论状态
        // 若审核不通过，通知发评论的用户，你的评论未被博主审核通过，原因是什么
        if (Objects.equals(status, CommentStatusEnum.EXAMINE_FAILED.getCode())
                && StringUtils.isNotBlank(mail)) {

            String reason = commentDO.getReason();
            String title = String.format("你在%s的评论未被审核通过", blogName);

            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<h2>审核未通过原因:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, reason, domain, routerUrl);
            mailHelper.sendHtml(mail, title, html);
        } else if (Objects.equals(status, CommentStatusEnum.NORMAL.getCode())) {
            // 如果是审核通过，通知发评论的用户，你的评论已经被博主审核通过
            String title = String.format("你在%s的评论已被审核通过", blogName);
            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, domain, routerUrl);
            mailHelper.sendHtml(mail, title, html);

            // 另外，还得通知被评论人，提示评论被回复了
            notifyBeCommentedUser(replyCommentId, blogName, nickname, content, domain);
        }


    }

    /**
     * 邮件通知被回复的用户
     * @param replyCommentId
     * @param blogName
     * @param nickname
     * @param content
     * @param domain
     */
    private void notifyBeCommentedUser(Long replyCommentId, String blogName, String nickname, String content, String domain) {
        if (Objects.isNull(replyCommentId))
            return;

        // 被回复的评论
        CommentDO replyCommentDO = commentMapper.selectById(replyCommentId);

        // 邮箱地址
        String to = replyCommentDO.getMail();

        // 邮箱判空
        if (StringUtils.isBlank(to))
            return;

        String routerUrl = replyCommentDO.getRouterUrl();
        String title = String.format("你在%s的评论收到了回复", blogName);

        String html = String.format("<html><body>" +
                        "<h2>你的评论:</h2><p>%s</p>" +
                        "<h2>%s 回复了你:</h2><p>%s</p>" +
                        "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                        "</body></html>",
                replyCommentDO.getContent(), nickname, content, domain, routerUrl);
        mailHelper.sendHtml(to, title, html);
    }
}
