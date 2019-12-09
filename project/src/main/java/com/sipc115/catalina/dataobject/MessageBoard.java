package com.sipc115.catalina.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class MessageBoard {
    /**留言id*/
    @Id
    @GeneratedValue
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
