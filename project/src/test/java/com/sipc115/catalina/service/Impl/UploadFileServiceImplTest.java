package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.UploadFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadFileServiceImplTest {

    @Autowired
    private UploadFileService uploadFileService;

    @Test
    public void deleteImage() {
        String URL="/Volumes/disk3/天津理工大学/程序员之路/115官网项目/sipc115_resources/images/dynamic_images/1.png";
        uploadFileService.deleteImage(URL);
    }
}