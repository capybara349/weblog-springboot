package com.capybara349.weblog.admin.service.impl;

import com.capybara349.weblog.admin.model.vo.user.FindUserInfoRspVO;
import com.capybara349.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.capybara349.weblog.admin.service.AdminUserService;
import com.capybara349.weblog.common.domain.mapper.UserMapper;
import com.capybara349.weblog.common.enums.ResponseCodeEnum;
import com.capybara349.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 01:07
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();

        String encodedPassword = passwordEncoder.encode(password);
        int count = userMapper.updatePasswordByUsername(username, encodedPassword);
        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @Override
    public Response findUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return Response.success(FindUserInfoRspVO.builder().username(username).build());
    }
}
