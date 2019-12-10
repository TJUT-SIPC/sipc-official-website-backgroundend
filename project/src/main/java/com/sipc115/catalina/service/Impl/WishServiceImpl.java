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

    /**
     * 1.分页查询所有寄语
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
     * 2.通过寄语审核状态查询+分页
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
     * 3.随机查询limit条寄语
     * @param limit
     * @return 查询到的寄语集合
     */
    @Override
    public List<Wishes> findWishesByLimit(Integer limit) {
        return wishRepository.findWishesByLimit(limit);
    }

    /**
     * 4.更新寄语状态
     * @param wishId 寄语id
     * @param wishStatus  寄语状态
     * @return
     */
    @Override
    public int updateWishStatus(Integer wishId, Integer wishStatus) {
        return wishRepository.updateWishStatus(wishId,wishStatus);
    }

    /**
     * 4.通过id删除一条寄语
     * @param wishId 寄语id
     */
    @Override
    public void delWish(Integer wishId) {
        wishRepository.delete(wishId);
    }
}
