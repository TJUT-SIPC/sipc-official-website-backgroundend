package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Dynamics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface DynamicRepository extends JpaRepository<Dynamics, Integer> {

    /**通过id查询一个动态*/
    @Override
    Optional<Dynamics> findById(Integer integer);

    /**分页查询动态*/
    @Override
    Page<Dynamics> findAll(Pageable pageable);

    /**通过id修改一个动态*/
    @Modifying
    @Transactional
    @Query("update Dynamics set dynamicImage=:dynamicImage, dynamicHeader=:dynamicHeader, dynamicText=:dynamicText," +
            "dynamicEditor=:dynamicEditor, dynamicCategory=:dynamicCategory where dynamicId=:dynamicId")
    int updateDynamicById(@Param("dynamicId")Integer DynamicId , @Param("dynamicImage")String dynamicImage, @Param("dynamicHeader")String dynamicHeader,
                          @Param("dynamicText")String dynamicText, @Param("dynamicEditor")String dynamicEditor,
                          @Param("dynamicCategory")Integer dynamicCategory);

    /**加入一个动态*/
    @Override
    Dynamics save(Dynamics dynamic);

    /**删除一个动态*/
    @Override
    void deleteById(Integer integer);
}
