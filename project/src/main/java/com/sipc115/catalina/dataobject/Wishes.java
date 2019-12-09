package com.sipc115.catalina.dataobject;


import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Wishes {

    /**寄语id*/
    @Id
    @GeneratedValue
    private Integer wishId;
    /**写寄语的人*/
    private String wishName;
    /**寄语的内容*/
    private String wishWord;
    /**寄语的审核状态*/
    private Integer wishStatus;


}
