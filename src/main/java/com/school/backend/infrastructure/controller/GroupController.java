package com.school.backend.infrastructure.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateGroupUseCase;
import com.school.backend.infrastructure.dto.group.CreateGroupRequest;
import com.school.backend.infrastructure.dto.group.GroupResponse;
import com.school.backend.infrastructure.persistence.entity.GroupEntity;
import com.school.backend.infrastructure.persistence.repository.GroupJpaRepository;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final CreateGroupUseCase createGroupUseCase;
    private final GroupJpaRepository groupJpaRepository;

    public GroupController(
        CreateGroupUseCase createGroupUseCase,
        GroupJpaRepository groupJpaRepository
    ) {
        this.createGroupUseCase = createGroupUseCase;
        this.groupJpaRepository = groupJpaRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public GroupResponse create(@RequestBody CreateGroupRequest request) {
        GroupEntity group = createGroupUseCase.execute(
            request.getName(),
            request.getTeacherId(),
            request.getStudentIds()
        );

        return new GroupResponse(
            group.getId(),
            group.getName(),
            group.getTeacher().getName(),
            group.getStudents().stream().map(student -> student.getName()).collect(java.util.stream.Collectors.toSet())
        );
    }

    @GetMapping("/my-groups")
    @PreAuthorize("hasRole('TEACHER')")
    public List<GroupResponse> myGroups(Authentication authentication) {

        String email = authentication.getName();

        return groupJpaRepository.findByTeacherEmail(email).stream()
            .map(group -> new GroupResponse(
                group.getId(),
                group.getName(),
                group.getTeacher().getName(),
                group.getStudents().stream().map(student -> student.getName()).collect(java.util.stream.Collectors.toSet())
            ))
            .toList();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public List<GroupResponse> getGroups(Authentication authentication) {

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return groupJpaRepository.findAll().stream()
                .map(group -> new GroupResponse(
                    group.getId(),
                    group.getName(),
                    group.getTeacher().getName(),
                    group.getStudents().stream().map(student -> student.getName()).collect(java.util.stream.Collectors.toSet())
                ))
                .toList();
        }

        String email = authentication.getName();

        return groupJpaRepository.findByTeacherEmail(email).stream()
            .map(group -> new GroupResponse(
                group.getId(),
                group.getName(),
                group.getTeacher().getName(),
                group.getStudents().stream().map(student -> student.getName()).collect(java.util.stream.Collectors.toSet())
            ))
            .toList();
    }
}
