package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Awards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface AwardRepository extends JpaRepository<Awards, Integer> {

    /**通过id查询一个奖项*/
    @Override
    Awards findOne(Integer integer);

    /**分页查询奖项*/
    Page<Awards> findAll(Pageable pageable);

    /**通过id修改一个奖项*/
    @Modifying
    @Transactional
    @Query("update Awards set awardName=:awardName, awardTime=:awardTime where awardId=:awardId")
    int updateAwardById(@Param("awardId")Integer awardId, @Param("awardName")String awardName, @Param("awardTime")Date awardTime);

    /**加入一个奖项*/
    @Override
    Awards save(Awards award);

    /**通过id删除一个奖项*/
    @Override
    void delete(Integer integer);
}
