package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.UploadFileService;

import com.sipc115.catalina.utils.URLUtil;
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

    private String projectImagePath;
    private String userHeadImagePath;
    private String dynamicImagePath;

    /**
     * 1.保存项目图片
     * @param projectImage 项目图片
     * @return 图片链接集合[原图,压缩图]
     * @throws IOException
     */
    @Override
    public List<String> uploadProjectImage(MultipartFile projectImage) throws IOException {

        //保存目录（需要修改）
        projectImagePath = URLUtil.getResourcePath() + "images/project_images/";

        //判断文件是否存在，不存在则创建
        File file = new File(projectImagePath);

        if(!file.exists()){
            file.mkdirs();
        }

        //生成唯一标识符
        UUID uuid = UUID.randomUUID();

        String newRawName = uuid + "_raw.png";
        String newRawPath = projectImagePath + newRawName;

        String newCompressName = uuid + "_compress.png";
        String newCompressPath =  projectImagePath + newCompressName;

        //保存原图
        projectImage.transferTo(new File( newRawPath));

        //压缩图片
        Thumbnails.of(newRawPath).scale(0.6f).toFile(newCompressPath);

        //返回原图与压缩图相对服务器链接集合
        String rawURL = newRawPath.substring(newRawPath.indexOf("sipc115_resources"),newRawPath.length());
        String compressURL = newCompressPath.substring(newCompressPath.indexOf("sipc115_resources"),newCompressPath.length());

        List<String> projectImageURLList = new ArrayList<>();

        projectImageURLList.add(compressURL);
        projectImageURLList.add(rawURL);

        return projectImageURLList;
    }

    /**
     * 2.保存用户头像
     * @param userHeadImage 用户头像
     * @return 头像链接集合[原图，压缩图]
     * @throws IOException
     */
    @Override
    public List<String> uploadUserHeadImage(MultipartFile userHeadImage) throws IOException {

        //保存目录（需要修改）
        userHeadImagePath = URLUtil.getResourcePath() + "images/user_head_images/";

        //判断文件是否存在，不存在则创建
        File file = new File(userHeadImagePath);

        if(!file.exists()){
            file.mkdirs();
        }

        //生成唯一标识符
        UUID uuid = UUID.randomUUID();

        String newRawName = uuid + "_raw.png";
        String newRawPath = userHeadImagePath + newRawName;

        String newCompressName = uuid + "_compress.png";
        String newCompressPath =  userHeadImagePath + newCompressName;

        //保存原图
        userHeadImage.transferTo(new File( newRawPath));

        //压缩图片
        Thumbnails.of(newRawPath).size(60,60).toFile(newCompressPath);

        //返回原图与压缩图相对服务器链接集合
        String rawURL = newRawPath.substring(newRawPath.indexOf("sipc115_resources"),newRawPath.length());
        String compressURL = newCompressPath.substring(newCompressPath.indexOf("sipc115_resources"),newCompressPath.length());

        List<String> userHeadImageURLlist = new ArrayList();

        userHeadImageURLlist.add(compressURL);
        userHeadImageURLlist.add(rawURL);

        return userHeadImageURLlist;
    }

    /**
     * 3.保存动态图片
     * @param dynamicImage 动态图片
     * @return  动态图链接集合[原图，压缩图]
     * @throws IOException
     */
    @Override
    public List<String> uploadDynamicImage(MultipartFile dynamicImage) throws IOException {
        //保存目录（需要修改）
        dynamicImagePath = URLUtil.getResourcePath() + "images/dynamic_images/";

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
        dynamicImage.transferTo(new File( newRawPath));

        //压缩图片
        Thumbnails.of(newRawPath).scale(0.6f).toFile(newCompressPath);

        //返回原图与压缩图相对服务器链接集合
        String rawURL = newRawPath.substring(newRawPath.indexOf("sipc115_resources"),newRawPath.length());
        String compressURL = newCompressPath.substring(newCompressPath.indexOf("sipc115_resources"),newCompressPath.length());

        List<String> dynamicImageURLList = new ArrayList();
        dynamicImageURLList.add(compressURL);
        dynamicImageURLList.add(rawURL);

        return dynamicImageURLList;
    }
}
