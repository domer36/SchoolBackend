package com.school.backend.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

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
                user.getPassword(),
                user.getRole()
        );

        UserEntity saved = springDataUserRepository.save(entity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPassword(),
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
                    entity.getPassword(),
                    entity.getRole()
            ))
            .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(e -> new User(e.getId(), e.getName(), e.getEmail(), e.getPassword(), e.getRole()));
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataUserRepository.findById(id)
                .map(e -> new User(e.getId(), e.getName(), e.getEmail(), e.getPassword(), e.getRole()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email);
    }
}
