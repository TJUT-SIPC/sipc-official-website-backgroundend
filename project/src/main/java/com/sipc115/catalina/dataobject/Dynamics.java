package com.sipc115.catalina.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Dynamics {

    /**动态id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dynamicId;
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
    private Integer dynamicCategory;

}
