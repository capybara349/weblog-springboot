package com.capybara349.weblog.admin.convert;

import com.capybara349.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.capybara349.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.capybara349.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 在 MapStruct 中，您不需要手动编写转换逻辑，MapStruct 会根据这个方法的签名自动生成转换的代码。
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:29
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
