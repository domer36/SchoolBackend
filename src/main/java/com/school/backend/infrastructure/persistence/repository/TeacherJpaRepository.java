package com.school.backend.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.backend.infrastructure.persistence.entity.TeacherEntity;

public interface TeacherJpaRepository extends JpaRepository<TeacherEntity, Long> {
} 
