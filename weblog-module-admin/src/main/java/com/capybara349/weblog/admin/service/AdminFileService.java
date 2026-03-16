package com.capybara349.weblog.admin.service;

import com.capybara349.weblog.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:13
 */
public interface AdminFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Response uploadFile(MultipartFile file);
}
