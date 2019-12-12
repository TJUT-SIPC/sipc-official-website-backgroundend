package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Wishes;
import com.sipc115.catalina.repository.WishRepository;
import com.sipc115.catalina.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    private WishRepository wishRepository;

    @Override
    public Wishes findOne(Integer wishId) {
        return wishRepository.findOne(wishId);
    }

    /**
     * 2.分页查询所有寄语
     * @param pageNum 页数
     * @param pageSize  一页显示多少条
     * @return 查询到的寄语集合
     */
    @Override
    public List<Wishes> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Wishes> page = wishRepository.findAll(pageable);
        return page.getContent();
    }

    /**
     * 3.分页查询不同状态寄语
     * @param wishStatus 寄语状态代码
     * @param pageNum   页数
     * @param pageSize  一页显示多少条
     * @return 查询到的寄语集合
     */
    @Override
    public List<Wishes> findWishesByWishStatus(Integer wishStatus ,Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Wishes> page = wishRepository.findWishesByWishStatus(wishStatus,pageable);
        return page.getContent();
    }

    /**
     * 4.随机查询limit条寄语
     * @param limit
     * @return 查询到的寄语集合
     */
    @Override
    public List<Wishes> findWishesByLimit(Integer limit) {
        return wishRepository.findWishesByLimit(limit);
    }

    /**
     * 5.更新寄语状态
     * @param wishId 寄语id
     * @param wishStatus  寄语状态
     * @return
     */
    @Override
    public int updateWishStatus(Integer wishId, Integer wishStatus) {
        return wishRepository.updateWishStatus(wishId,wishStatus);
    }

    /**
     * 6.添加一条寄语
     * @param wish 寄语对象
     * @return 基于对象
     */
    @Override
    public Wishes addWish(Wishes wish) {
        return wishRepository.save(wish);
    }

    /**
     * 7.通过id删除一条寄语
     * @param wishId 寄语id
     */
    @Override
    public void delWish(Integer wishId) {
        wishRepository.delete(wishId);
    }
}
