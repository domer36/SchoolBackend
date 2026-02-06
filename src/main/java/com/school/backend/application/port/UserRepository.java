package com.school.backend.application.port;

import com.school.backend.domain.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findAll();
}
