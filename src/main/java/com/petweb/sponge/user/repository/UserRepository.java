package com.petweb.sponge.user.repository;

import com.petweb.sponge.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
