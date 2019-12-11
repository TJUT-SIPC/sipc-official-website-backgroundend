package com.sipc115.catalina.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface UploadFileService {

    /**上传项目图片*/
    List<String> uploadProjectImage(MultipartFile upload) throws IOException;

}
