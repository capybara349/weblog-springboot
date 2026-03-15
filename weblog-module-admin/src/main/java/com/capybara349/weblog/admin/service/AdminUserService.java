package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.capybara349.weblog.common.utils.Response;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 01:06
 */
public interface AdminUserService {
    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
    /**
     * 获取当前登录用户信息
     * @return
     */
    Response findUserInfo();
}
