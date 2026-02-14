package com.school.backend.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateGroupUseCase;
import com.school.backend.infrastructure.dto.group.CreateGroupRequest;
import com.school.backend.infrastructure.dto.group.GroupResponse;
import com.school.backend.infrastructure.persistence.entity.GroupEntity;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final CreateGroupUseCase createGroupUseCase;

    public GroupController(CreateGroupUseCase createGroupUseCase) {
        this.createGroupUseCase = createGroupUseCase;
    }

    @PostMapping
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
}
