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
     * 1.分页查询所有留言功能
     * @param page      当前页数
     * @param pageSize  一页查询多少条
     * @return
     */
    @PostMapping("/messageCenter/getMessage")
    @LoginRequired
    public ResultVO getMessage_ADMIN(Integer page, Integer pageSize){

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
     * 2.通过id删除一条留言
     * @param id
     * @return
     */
    @LoginRequired
    @PostMapping("/messageCenter/delMessage")
    public ResultVO delMessage_ADMIN(Integer id){
        messageBoardService.delMessage(id);
        return new ResultVO(0,"success");
    }


}
