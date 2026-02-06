package com.school.backend.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.model.User;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public User save(User user) {
        User savedUser = new User(
            idCounter++,
            user.getName(),
            user.getEmail(),
            user.getRole()
        );
        users.add(savedUser);
        return savedUser;
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
