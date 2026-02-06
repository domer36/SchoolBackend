package com.school.backend.application.usecase;

import java.util.List;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.model.User;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.findAll();
    }
}
