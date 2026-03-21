package com.capybara349.weblog.web.convert;

import com.capybara349.weblog.common.domain.dos.CommentDO;
import com.capybara349.weblog.web.model.vo.comment.FindCommentItemRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.20 17:06
 */
@Mapper
public interface CommentConvert {
    /**
     * 初始化 convert 实例
     */
    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    /**
     * CommentDO -> FindCommentItemRspVO
     * @param bean
     * @return
     */
    FindCommentItemRspVO convertDO2VO(CommentDO bean);

}
