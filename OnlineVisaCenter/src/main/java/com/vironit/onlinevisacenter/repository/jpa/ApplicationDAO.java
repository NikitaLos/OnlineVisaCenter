package com.vironit.onlinevisacenter.repository.jpa;

import com.vironit.onlinevisacenter.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDAO extends JpaRepository<Application,Integer> {
    @Query("select a from Application a where a.user.id = :userId")
    List<Application> findByUserId(@Param("userId") Integer userId);
}
