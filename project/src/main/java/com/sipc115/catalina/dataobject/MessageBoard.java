package com.sipc115.catalina.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class MessageBoard {
    /**留言id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;
    /**留言者邮箱*/
    private String boardEmail;
    /**留言者称呼*/
    private String boardNickname;
    /**建议*/
    private String boardAdvice;
    /**上传时间*/
    private Date boardUploadTime;

}
