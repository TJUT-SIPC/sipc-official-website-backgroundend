package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.MessageBoard;
import com.sipc115.catalina.repository.MessageBoardRepository;
import com.sipc115.catalina.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    private MessageBoardRepository messageBoardRepository;

    /**
     * 1.通过id查询一条留言
     * @param messageBoardId 留言id
     * @return 留言对象
     */
    @Override
    public MessageBoard findOne(Integer messageBoardId) {
        return messageBoardRepository.findOne(messageBoardId);
    }

    /**
     * 2.分页查询所有留言
     * @param pageNum 页数
     * @param pageSize 一页显示多少条
     * @return 查询到的留言集合
     */
    @Override
    public List<MessageBoard> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<MessageBoard> page = messageBoardRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public MessageBoard addMessage(MessageBoard message) {
        return messageBoardRepository.save(message);
    }

    /**
     * 4.删除一条留言
     * @param messageBoardId 留言id
     */
    @Override
    public void delMessage(Integer messageBoardId) {
        messageBoardRepository.delete(messageBoardId);
    }
}
