package com.school.backend.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateTeacherUseCase;
import com.school.backend.infrastructure.dto.teacher.CreateTeacherRequest;
import com.school.backend.infrastructure.dto.teacher.TeacherResponse;
import com.school.backend.infrastructure.persistence.repository.TeacherJpaRepository;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final CreateTeacherUseCase createTeacherUseCase;
    private final TeacherJpaRepository teacherRepository;

    public TeacherController(CreateTeacherUseCase createTeacherUseCase, TeacherJpaRepository teacherRepository) {
        this.createTeacherUseCase = createTeacherUseCase;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping
    public TeacherResponse create(@RequestBody CreateTeacherRequest request) {
        var teacher = createTeacherUseCase.execute(
            request.getName(),
            request.getEmail()
        );

        return new TeacherResponse(
            teacher.getId(),
            teacher.getName(),
            teacher.getEmail()
        );
    }

    @GetMapping
    public List<TeacherResponse> getAll() {
        return teacherRepository.findAll()
            .stream()
            .map(teacher -> new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail()
            ))
            .toList();
    }
}
