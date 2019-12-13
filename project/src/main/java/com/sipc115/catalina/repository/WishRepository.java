package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Wishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wishes, Integer> {

    /**通过id查询一条寄语*/
    @Override
    Optional<Wishes> findById(Integer integer);

    /**分页查询所有寄语*/
    Page<Wishes> findAll(Pageable pageable);

    /**分页查询不同状态寄语*/
    @Query(value = "select * from `wishes` where wish_status=?1 \n#pageable\n",
    nativeQuery = true)
    Page<Wishes> findWishesByWishStatus(Integer wishStatus, Pageable pageable);

   // @Query(nativeQuery = true, value = "SELECT * from `wishes` where wish_status =:wishStatus")
   // List<Wishes> findWishesByWishStatus(@Param("wishStatus") Integer wishStatus);

    /**随机查询n条已发布的寄语*/
    @Query(nativeQuery = true, value = "SELECT * from `wishes` where wish_status= 1 order by rand() LIMIT ?1")
    List<Wishes> findWishesByLimit(Integer limit);

    /**通过id修改寄语状态*/
    @Modifying
    @Transactional
    @Query("update Wishes set wishStatus=:wishStatus where wishId=:wishId")
    int updateWishStatus(@Param("wishId")Integer wishId, @Param("wishStatus")Integer wishStatus);


    /**添加一条寄语*/
    @Override
    Wishes save(Wishes wish);

    /**通过id删除一个寄语*/
    @Override
    void deleteById(Integer integer);
}
