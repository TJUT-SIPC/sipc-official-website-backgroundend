package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Projects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Integer> {

    /**通过id查询一个项目*/
    @Override
    Optional<Projects> findById(Integer integer);

    /**分页查询项目*/
    Page<Projects> findAll(Pageable pageable);

    /**通过id修改一个项目*/
    @Modifying
    @Transactional
    @Query("update Projects set projectDescription=:projectDescription, projectTime=:projectTime, " +
            "projectImageRaw=:projectImageRaw, projectImageCompress=:projectImageCompress where projectId=:projectId")
    int updateProjectById(@Param("projectId")Integer projectId, @Param("projectDescription") String projectDescription,
                          @Param("projectTime")Date projectTime, @Param("projectImageRaw")String projectImageRaw,
                          @Param("projectImageCompress")String projectImageCompress);

    /**加入一个项目*/
    @Override
    Projects save(Projects project);

    /**通过id删除一个项目*/
    @Override
    void deleteById(Integer integer);
}
