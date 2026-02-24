package com.school.backend.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.model.User;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String name, String email, String password, String role) {

        String encodedPassword = passwordEncoder.encode(password);
        
        User user = new User(
            null, 
            name,
            email,
            encodedPassword,
            role
        );

        return userRepository.save(user);
    }

}
