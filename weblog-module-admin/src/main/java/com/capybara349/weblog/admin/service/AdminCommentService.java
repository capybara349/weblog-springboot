package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.admin.model.vo.comment.DeleteCommentReqVO;
import com.capybara349.weblog.admin.model.vo.comment.ExamineCommentReqVO;
import com.capybara349.weblog.admin.model.vo.comment.FindCommentPageListReqVO;
import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 10:33
 */
public interface AdminCommentService {
    /**
     * 查询评论分页数据
     * @param findCommentPageListReqVO
     * @return
     */
    Response findCommentPageList(FindCommentPageListReqVO findCommentPageListReqVO);
    /**
     * 删除评论
     * @param deleteCommentReqVO
     * @return
     */
    Response deleteComment(DeleteCommentReqVO deleteCommentReqVO);
    /**
     * 评论审核
     * @param examineCommentReqVO
     * @return
     */
    Response examine(ExamineCommentReqVO examineCommentReqVO);
}
