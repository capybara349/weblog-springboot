package com.capybara349.weblog.admin.service.impl;

import com.capybara349.weblog.admin.model.vo.file.UploadFileRspVO;
import com.capybara349.weblog.admin.service.AdminFileService;
import com.capybara349.weblog.admin.utils.MinioUtil;
import com.capybara349.weblog.common.enums.ResponseCodeEnum;
import com.capybara349.weblog.common.exception.BizException;
import com.capybara349.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * &#064;author capybara349 </br>
 * &#064;date 2026.03.16 22:13
 */
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);

            // 构建成功返参，将图片的访问链接返回
            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("==> 上传文件至 Minio 错误: ", e);
            // 手动抛出业务异常，提示 “文件上传失败”
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}

