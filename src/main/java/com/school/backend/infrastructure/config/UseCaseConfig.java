package com.school.backend.infrastructure.config;

import com.school.backend.application.port.UserRepository;
import com.school.backend.application.usecase.CreateUserUseCase;
import com.school.backend.application.usecase.GetUserByIdUseCase;
import com.school.backend.application.usecase.GetUsersUseCase;
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
}
