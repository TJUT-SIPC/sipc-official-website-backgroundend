package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Wishes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishService {

    /**分页查询所有寄语*/
    List<Wishes> findAll(Integer pageNum, Integer pageSize);

    /**分页查询不同状态寄语*/
    List<Wishes> findWishesByWishStatus(Integer wishStatus, Integer pageNum, Integer pageSize);

    /**分页随机查询n条已发布寄语*/
    List<Wishes> findWishesByLimit(Integer limit);

    /**通过id修改寄语状态*/
    int updateWishStatus(Integer wishId, Integer wishStatus);

    /**添加一条寄语*/
    Wishes addWish(Wishes wish);

    /**通过id删除一条寄语*/
    void delWish(Integer wishId);

}
