package com.school.backend.application.usecase;

import com.school.backend.domain.exception.BusinessException;
import com.school.backend.infrastructure.persistence.entity.TeacherEntity;
import com.school.backend.infrastructure.persistence.repository.TeacherJpaRepository;

public class CreateTeacherUseCase {
    private final TeacherJpaRepository  teacherRepository;

    public CreateTeacherUseCase(TeacherJpaRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }   

    public TeacherEntity execute(String name, String email) {

        if (email == null || email.isEmpty()) {
            throw new BusinessException("Email is required");
        }
        
        TeacherEntity teacher = new TeacherEntity(name, email);
        return teacherRepository.save(teacher);
    }
}
