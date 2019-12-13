package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.MessageBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageBoardRepository extends JpaRepository<MessageBoard, Integer> {

    /**通过id查询一条留言*/
    @Override
    Optional<MessageBoard> findById(Integer integer);

    /**分页查询留言*/
    @Override
    Page<MessageBoard> findAll(Pageable pageable);

    /**添加一条留言*/
    @Override
    MessageBoard save(MessageBoard message);

    /**删除一条留言*/
    @Override
    void deleteById(Integer integer);
}
