package com.school.backend.infrastructure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.infrastructure.dto.user.CreateUserRequest;
import com.school.backend.infrastructure.dto.user.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<UserResponse> users = new ArrayList<>();
    private Long idCounter = 1L;

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        UserResponse user = new UserResponse(
            idCounter++,
            request.getName(),
            request.getEmail(),
            request.getRole()
        );

        users.add(user);
        return user;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return users;
    }

}
