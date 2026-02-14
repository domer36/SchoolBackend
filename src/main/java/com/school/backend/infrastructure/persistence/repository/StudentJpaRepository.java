package com.school.backend.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.backend.infrastructure.persistence.entity.StudentEntity;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {

}
