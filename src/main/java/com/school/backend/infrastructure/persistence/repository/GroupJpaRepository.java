package com.school.backend.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.backend.infrastructure.persistence.entity.GroupEntity;

public interface GroupJpaRepository extends JpaRepository<GroupEntity, Long> {
    @Query("""
        SELECT g FROM GroupEntity g
        WHERE g.teacher.email = :email
    """)
    List<GroupEntity> findByTeacherEmail(@Param("email") String email);

}
