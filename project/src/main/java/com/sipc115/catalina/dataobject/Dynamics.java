package com.sipc115.catalina.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Dynamics {

    /**动态id*/
    @Id
    @GeneratedValue
    private Integer id;
    /**动态图片URL*/
    private String dynamicImage;
    /**动态标题*/
    private String dynamicHeader;
    /**动态内容*/
    private String dynamicText;
    /**动态提交时间*/
    private Date dynamicTime;
    /**动态提交者*/
    private String dynamicEditor;
    /**动态分类编号*/
    private Integer dynamicCatagory;

}
