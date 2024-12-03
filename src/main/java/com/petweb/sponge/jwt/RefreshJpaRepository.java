package com.petweb.sponge.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {


    @Query("SELECT CASE WHEN COUNT(refresh) > 0 THEN true ELSE false END FROM RefreshTokenEntity refresh WHERE refresh.refreshToken = :refreshToken")
    Boolean existsByRefreshToken(@Param("refreshToken") String refreshToken);

    @Modifying
    @Query("DELETE FROM RefreshTokenEntity refresh WHERE refresh.refreshToken = :refreshToken")
    void deleteByRefreshToken(@Param("refreshToken") String refreshToken);

}
