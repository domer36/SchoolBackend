package com.school.backend.application.usecase;

import com.school.backend.domain.exception.BusinessException;
import com.school.backend.infrastructure.persistence.entity.StudentEntity;
import com.school.backend.infrastructure.persistence.repository.StudentJpaRepository;

public class CreateStudentUseCase {
    private final StudentJpaRepository studentRepository;

    public CreateStudentUseCase(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity execute(String name, String email) {

        if (email == null || email.isEmpty()) {
            throw new BusinessException("Email is required");
        }
        StudentEntity student = new StudentEntity(name, email);
        return studentRepository.save(student);
    }
}
