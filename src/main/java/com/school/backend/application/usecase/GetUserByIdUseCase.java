package com.school.backend.application.usecase;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.exception.NotFoundException;
import com.school.backend.domain.model.User;

public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
