package com.sipc115.catalina.dataobject;


import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Wishes {

    /**寄语id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishId;
    /**写寄语的人*/
    private String wishName;
    /**寄语的内容*/
    private String wishWord;
    /**寄语的审核状态*/
    private Integer wishStatus;
    /**寄语的提交时间*/
    private Date wishTime;

}
