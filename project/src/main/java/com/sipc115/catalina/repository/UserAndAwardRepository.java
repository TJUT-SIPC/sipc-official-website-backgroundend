package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.UserAndAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserAndAwardRepository extends JpaRepository<UserAndAward, Integer> {

    /**
     * 1.通过用户id删除其关联的奖项
     * @param userId
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from `users_awards` where user_id=?1")
    @Override
    void delete(Integer userId);
}
