package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.MessageBoard;
import com.sipc115.catalina.service.MessageBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class SendMessageController {

    @Autowired
    private MessageBoardService messageBoardService;

    @PostMapping("/sendMessage")
    public List<ResultVO> list(String email, String nickname, String advice){

        List<ResultVO> resultVOList = new ArrayList<>();

        /**验证参数*/
        boolean rightEmail = false;
        String emailRegex = "\\w{1,}@\\w{1,}.\\w{1,}";

        if(email.matches(emailRegex)){
            rightEmail = true;
        }

        System.out.println("邮箱"+rightEmail);
        System.out.println(nickname);
        System.out.println(advice);

        if(rightEmail && nickname!=null & advice!=null) {
            /**保存建议*/
            MessageBoard message = new MessageBoard();
            message.setBoardEmail(email);
            message.setBoardNickname(nickname);
            message.setBoardAdvice(advice);
            messageBoardService.addMessage(message);

            resultVOList.add(new ResultVO(0,"提交成功"));
        }
        if(!rightEmail){
            resultVOList.add(new ResultVO(1,"邮箱格式错误"));
        }
        if(nickname==null || nickname.trim().isEmpty()){
            resultVOList.add(new ResultVO(2,"名字不能为空"));
        }
        if(advice==null|| advice.trim().isEmpty()){
            resultVOList.add(new ResultVO(3,"建议不能为空"));
        }

        return resultVOList;
    }

}
