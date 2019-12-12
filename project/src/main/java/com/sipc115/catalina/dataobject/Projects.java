package com.sipc115.catalina.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Projects {

    /**项目编号*/
    @Id
    @GeneratedValue
    private Integer projectId;
    /**项目描述*/
    private String projectDescription;
    /**项目时间*/
    private Date projectTime;
    /**项目原图URL*/
    private String projectImageRaw;
    /**项目缩略图URL*/
    private String projectImageCompress;

}
