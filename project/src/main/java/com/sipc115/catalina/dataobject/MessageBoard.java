package com.sipc115.catalina.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "传入的邮箱不能为空")
    @Email(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$",message = "邮箱格式错误")
    private String boardEmail;
    /**留言者称呼*/
    @NotNull(message = "留言者名字不能为空")
    private String boardNickname;
    /**建议*/
    @NotNull(message = "留言者建议不能为空")
    private String boardAdvice;
    /**上传时间*/
    private Date boardUploadTime;

}
