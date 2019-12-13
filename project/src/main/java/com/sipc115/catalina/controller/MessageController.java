package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.MessageVO.MessageListInfoVO;
import com.sipc115.catalina.VO.MessageVO.MessageListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.MessageBoard;
import com.sipc115.catalina.service.MessageBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageBoardService messageBoardService;

    /**
     * 1.首页上传留言功能
     * @param email     联系邮箱
     * @param nickname  昵称
     * @param advice    建议
     * @return
     */
    @PostMapping("/sendMessage")
    public ResultVO sendMessage(String email, String nickname, String advice){

        /**验证参数*/
        boolean rightEmail = false;
        String emailRegex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$";

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

    /**
     * 2.分页查询所有留言功能
     * @param page      当前页数
     * @param pageSize  一页查询多少条
     * @return
     */
    @PostMapping("/messageCenter/getMessage")
    @LoginRequired
    public ResultVO getMessage(Integer page, Integer pageSize){

        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if(pageSize<5) pageSize=5;
        if(pageSize>100) pageSize=100;

        //1.查询所有留言
        List<MessageBoard> messageList = messageBoardService.findAll(page-1, pageSize);

        //2.数据组装
        MessageListVO messageListVO = new MessageListVO();

        List<MessageListInfoVO> messageListInfoVOList = new ArrayList();

        for(MessageBoard message : messageList){

            MessageListInfoVO messageListInfoVO = new MessageListInfoVO();

            //传入id，email，name，advice，upload_time
            messageListInfoVO.setMessageId(message.getBoardId());
            messageListInfoVO.setMessageEmail(message.getBoardEmail());
            messageListInfoVO.setMessageNickname(message.getBoardNickname());
            messageListInfoVO.setMessageAdvice(message.getBoardAdvice());
            messageListInfoVO.setMessageUploadTime(sdf.format(message.getBoardUploadTime()));

            messageListInfoVOList.add(messageListInfoVO);
        }

        messageListVO.setMessageListInfoVOList(messageListInfoVOList);
        messageListVO.setTotal_message(messageListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(messageListVO);

        return resultVO;
    }

    /**
     * 3.通过id删除一条留言
     * @param id
     * @return
     */
    @LoginRequired
    @PostMapping("/messageCenter/delMessage")
    public ResultVO delMessage(Integer id){
        messageBoardService.delMessage(id);
        return new ResultVO(0,"success");
    }


}
