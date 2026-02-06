package com.school.backend.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateUserUseCase;
import com.school.backend.domain.model.User;
import com.school.backend.infrastructure.dto.user.CreateUserRequest;
import com.school.backend.infrastructure.dto.user.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User user = createUserUseCase.execute(
            request.getName(),
            request.getEmail(),
            request.getRole()
        );

        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole()
        );
    }
}
