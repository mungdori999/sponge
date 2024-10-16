package com.petweb.sponge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT user FROM UserEntity user where user.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM UserAddressEntity addr where addr.userEntity.id = :id")
    void deleteAddress(@Param("id") Long id);

}