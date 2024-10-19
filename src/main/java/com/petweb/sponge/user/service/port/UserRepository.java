package com.petweb.sponge.user.service.port;

import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.repository.UserEntity;

import java.util.Optional;

public interface UserRepository  {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
    User register(User user);
    void delete(User user);
}
