package com.school.backend.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.backend.infrastructure.persistence.entity.GroupEntity;

public interface GroupJpaRepository extends JpaRepository<GroupEntity, Long> {

}
