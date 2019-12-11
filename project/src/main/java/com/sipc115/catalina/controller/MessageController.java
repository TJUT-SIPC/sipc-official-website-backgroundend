package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.MessageBoard;
import com.sipc115.catalina.service.MessageBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageBoardService messageBoardService;

    @PostMapping("/sendMessage")
    public ResultVO sendMessage(String email, String nickname, String advice){

        /**验证参数*/
        boolean rightEmail = false;
        String emailRegex = "\\w{1,}@\\w{1,}.\\w{1,}";

        if(email.matches(emailRegex)){
            rightEmail = true;
        }

        if(rightEmail && nickname!=null && !nickname.trim().isEmpty() && advice!=null && !advice.trim().isEmpty() ) {
            /**保存建议*/
            MessageBoard message = new MessageBoard();
            message.setBoardEmail(email);
            message.setBoardNickname(nickname);
            message.setBoardAdvice(advice);
            messageBoardService.addMessage(message);

            return new ResultVO(0,"success");
        }
        if(!rightEmail){
            return new ResultVO(1,"邮箱格式错误");
        }
        if(nickname==null || nickname.trim().isEmpty()){
            return new ResultVO(2,"名字不能为空");
        }
        if(advice==null || advice.trim().isEmpty()){
            return new ResultVO(3,"建议不能为空");
        }

        return new ResultVO(0,"提交成功");
    }

}
