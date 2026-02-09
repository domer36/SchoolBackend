package com.school.backend.application.usecase;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.exception.BusinessException;
import com.school.backend.domain.model.User;

public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String name, String email, String role) {

        if (email == null || email.isEmpty()) {
            throw new BusinessException("Email is required");
        }
        
        User user = new User(
            null, 
            name,
            email,
            role
        );

        return userRepository.save(user);
    }

}
