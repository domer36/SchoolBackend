package com.school.backend.infrastructure.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateStudentUseCase;
import com.school.backend.infrastructure.dto.student.CreateStudentRequest;
import com.school.backend.infrastructure.dto.student.StudentResponse;
import com.school.backend.infrastructure.persistence.repository.StudentJpaRepository;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final CreateStudentUseCase createStudentUseCase;
    private final StudentJpaRepository studentRepository;

    public StudentController(CreateStudentUseCase createStudentUseCase, StudentJpaRepository studentRepository) {
        this.createStudentUseCase = createStudentUseCase;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse create(@RequestBody CreateStudentRequest request) {
        var student = createStudentUseCase.execute(
            request.getName(),
            request.getEmail()
        );

        return new StudentResponse(
            student.getId(),
            student.getName(),
            student.getEmail()
        );
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
            .stream()
            .map(student -> new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
            ))
            .toList();
    }
}
