package com.school.backend.infrastructure.config;

import com.school.backend.application.port.UserRepository;
import com.school.backend.application.usecase.CreateGroupUseCase;
import com.school.backend.application.usecase.CreateStudentUseCase;
import com.school.backend.application.usecase.CreateTeacherUseCase;
import com.school.backend.application.usecase.CreateUserUseCase;
import com.school.backend.application.usecase.GetUserByIdUseCase;
import com.school.backend.application.usecase.GetUsersUseCase;
import com.school.backend.infrastructure.persistence.repository.GroupJpaRepository;
import com.school.backend.infrastructure.persistence.repository.StudentJpaRepository;
import com.school.backend.infrastructure.persistence.repository.TeacherJpaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public GetUsersUseCase getUsersUseCase(UserRepository userRepository) {
        return new GetUsersUseCase(userRepository);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(UserRepository userRepository) {
        return new GetUserByIdUseCase(userRepository);
    }

    @Bean
    public CreateGroupUseCase createGroupUseCase(
        GroupJpaRepository groupRepository,
        TeacherJpaRepository teacherRepository,
        StudentJpaRepository studentRepository
    ) {
        return new CreateGroupUseCase(groupRepository, teacherRepository, studentRepository);
    }

    @Bean
    public CreateTeacherUseCase createTeacherUseCase(TeacherJpaRepository teacherRepository) {
        return new CreateTeacherUseCase(teacherRepository);
    }

    @Bean
    public CreateStudentUseCase createStudentUseCase(StudentJpaRepository studentRepository) {
        return new CreateStudentUseCase(studentRepository);
    }
}
