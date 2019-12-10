package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.MessageBoard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageBoardService {

    /**单条留言查询*/
    MessageBoard findOne(Integer messageBoardId);

    /**分页查询留言*/
    List<MessageBoard> findAll(Integer pageNum, Integer pageSize);

    /**添加留言*/
    MessageBoard addMessage(MessageBoard message);

    /**删除留言*/
    void delMessage(Integer messageBoardId);

}
