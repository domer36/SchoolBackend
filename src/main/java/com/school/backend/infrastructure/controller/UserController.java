package com.school.backend.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.application.usecase.CreateUserUseCase;
import com.school.backend.application.usecase.GetUsersUseCase;
import com.school.backend.domain.model.User;
import com.school.backend.infrastructure.dto.user.CreateUserRequest;
import com.school.backend.infrastructure.dto.user.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;

    public UserController(
        CreateUserUseCase createUserUseCase,
        GetUsersUseCase getUsersUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.getUsersUseCase = getUsersUseCase;
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

    @GetMapping
    public List<UserResponse> getUsers() {
        return getUsersUseCase.execute()
            .stream()
            .map(user -> new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
            ))
            .toList();
    }
}
