package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ImageVO.ImageVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.enums.ImageQualityEnum;
import com.sipc115.catalina.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 1.上传用户头像接口
     * @param headImage     用后头像
     * @return              ResultVO
     */
    @PostMapping("/uploadHeadImage")
    @LoginRequired
    public ResultVO uploadHeadImage_ADMIN(@RequestParam("headImage") MultipartFile headImage) throws IOException {

        List<String> headImageList = uploadFileService.uploadUserHeadImage(headImage);

        //封装视图
        ImageVO imageVO = new ImageVO();

        imageVO.setCompressImageURL(headImageList.get(ImageQualityEnum.LOW.getCode()));
        imageVO.setRawImageURL(headImageList.get(ImageQualityEnum.RAW.getCode()));

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(imageVO);

        return resultVO;
    }

    /**
     * 2.上传项目图片接口
     * @param projectImage     项目图片
     * @return                 ResultVO
     * @throws IOException
     */
    @PostMapping("/uploadProjectImage")
    @LoginRequired
    public ResultVO uploadProjectImage_ADMIN(@RequestParam("projectImage") MultipartFile projectImage) throws IOException {

        List<String> projectImageList = uploadFileService.uploadProjectImage(projectImage);

        //封装视图
        ImageVO imageVO = new ImageVO();

        imageVO.setCompressImageURL(projectImageList.get(ImageQualityEnum.LOW.getCode()));
        imageVO.setRawImageURL(projectImageList.get(ImageQualityEnum.RAW.getCode()));

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(imageVO);

        return resultVO;

    }

    /**
     * 3.上传动态图片接口
     * @param dynamicImage  动态图片
     * @return              ResultVO
     */
    @PostMapping("/uploadDynamicImage")
    @LoginRequired
    public ResultVO uploadDynamicImage_ADMIN(@RequestParam("dynamicImage") MultipartFile dynamicImage) throws IOException {

        List<String> dynamicImageList = uploadFileService.uploadDynamicImage(dynamicImage);

        //封装视图
        ImageVO imageVO = new ImageVO();

        imageVO.setCompressImageURL(dynamicImageList.get(ImageQualityEnum.LOW.getCode()));
        imageVO.setRawImageURL(dynamicImageList.get(ImageQualityEnum.RAW.getCode()));

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(imageVO);

        return resultVO;

    }
}
