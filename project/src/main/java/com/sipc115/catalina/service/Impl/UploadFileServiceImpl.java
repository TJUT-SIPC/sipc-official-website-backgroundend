package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.UploadFileService;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private String dynamicImagePath;

    @Override
    public List<String> uploadProjectImage(MultipartFile projectImage) throws IOException {

        //保存目录（需要修改）
        dynamicImagePath =  "/Volumes/disk3/天津理工大学/程序员之路/115官网项目/sipc115_resources/images/dynamic_images/";

        //判断文件是否存在，不存在则创建
        File file = new File(dynamicImagePath);

        if(!file.exists()){
            file.mkdirs();
        }

        //生成唯一标识符
        UUID uuid = UUID.randomUUID();

        String newRawName = uuid + "_raw.png";
        String newRawPath = dynamicImagePath + newRawName;

        String newCompressName = uuid + "_compress.png";
        String newCompressPath =  dynamicImagePath + newCompressName;

        //保存原图
        projectImage.transferTo(new File( newRawPath));

        //压缩图片
        Thumbnails.of(newRawPath).scale(0.12f).toFile(newCompressPath);

        //返回原图与压缩图相对服务器链接集合
        String s1 = newRawPath.substring(newRawPath.indexOf("sipc115_resources"),newRawPath.length());
        String s2 = newCompressPath.substring(newCompressPath.indexOf("sipc115_resources"),newCompressPath.length());

        List<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        return list;
    }
}
