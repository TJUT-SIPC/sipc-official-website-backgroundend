package com.sipc115.catalina.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserAndAward {

    @Id
    @GeneratedValue
    private Integer Id;

    private Integer userId;

    private Integer awardId;

}
