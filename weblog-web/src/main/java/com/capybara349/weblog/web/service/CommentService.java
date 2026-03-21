package com.capybara349.weblog.web.service;

import com.capybara349.weblog.common.utils.Response;
import com.capybara349.weblog.web.model.vo.comment.FindCommentListReqVO;
import com.capybara349.weblog.web.model.vo.comment.FindQQUserInfoReqVO;
import com.capybara349.weblog.web.model.vo.comment.PublishCommentReqVO;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 16:35
 */
public interface CommentService {

    /**
     * 根据 QQ 号获取用户信息
     * @param findQQUserInfoReqVO
     * @return
     */
    Response findQQUserInfo(FindQQUserInfoReqVO findQQUserInfoReqVO);

    /**
     * 发布评论
     *
     * @param publishCommentReqVO
     * @return
     */
    Response publishComment(PublishCommentReqVO publishCommentReqVO);
    /**
     * 查询页面所有评论
     * @param findCommentListReqVO
     * @return
     */
    Response findCommentList(FindCommentListReqVO findCommentListReqVO);

}

