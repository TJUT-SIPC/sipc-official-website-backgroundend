package com.sipc115.catalina.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Awards {
    /**奖项编号*/
    @Id
    @GeneratedValue
    private Integer awardId;
    /**奖项名称*/
    private String awardName;
    /**奖项时间*/
    private Date awardTime;

}
