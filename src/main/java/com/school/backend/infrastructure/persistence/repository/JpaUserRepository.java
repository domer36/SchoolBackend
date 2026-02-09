package com.school.backend.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.school.backend.application.port.UserRepository;
import com.school.backend.domain.model.User;
import com.school.backend.infrastructure.persistence.entity.UserEntity;

@Repository
@Primary
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository springDataUserRepository;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
         UserEntity entity = new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        UserEntity saved = springDataUserRepository.save(entity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole()
        );
    }

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll()
            .stream()
            .map(entity -> new User(
                    entity.getId(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getRole()
            ))
            .toList();
    }
}
