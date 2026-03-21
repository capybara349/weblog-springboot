package com.capybara349.weblog.admin.convert;

import com.capybara349.weblog.admin.model.vo.comment.FindCommentPageListRspVO;
import com.capybara349.weblog.common.domain.dos.CommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.21 10:33
 */
@Mapper
public interface CommentConvert {
    /**
     * 初始化 convert 实例
     */
    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindCommentPageListRspVO convertDO2VO(CommentDO bean);

}
