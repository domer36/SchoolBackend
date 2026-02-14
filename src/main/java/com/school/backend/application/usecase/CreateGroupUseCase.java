package com.school.backend.application.usecase;

import java.util.Set;

import com.school.backend.domain.exception.NotFoundException;
import com.school.backend.infrastructure.persistence.entity.GroupEntity;
import com.school.backend.infrastructure.persistence.entity.TeacherEntity;
import com.school.backend.infrastructure.persistence.repository.GroupJpaRepository;
import com.school.backend.infrastructure.persistence.repository.StudentJpaRepository;
import com.school.backend.infrastructure.persistence.repository.TeacherJpaRepository;

public class CreateGroupUseCase {
    private final GroupJpaRepository groupRepository;
    private final TeacherJpaRepository teacherRepository;
    private final StudentJpaRepository studentRepository;

    public CreateGroupUseCase(
        GroupJpaRepository groupRepository,
        TeacherJpaRepository teacherRepository,
        StudentJpaRepository studentRepository
    ) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

        public GroupEntity execute(String name, Long teacherId, Set<Long> studentIds) {
            TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));

        GroupEntity group = new GroupEntity(name, teacher);

        studentIds.forEach(studentId -> {
            studentRepository.findById(studentId).ifPresent(group::addStudent);
        });

        return groupRepository.save(group);
    }
}
