package com.petweb.sponge.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshRepositoryImpl implements RefreshRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.jwt.refresh-expire-length}")
    private long refreshExpireLong;

    @Override
    public void save(String refreshToken) {
        redisTemplate.opsForValue().set(
                refreshToken,
                "refreshtoken",
                refreshExpireLong
        );
    }

    @Override
    public Boolean existsByRefresh(String refreshToken) {
        return redisTemplate.hasKey(refreshToken);
    }

    @Override
    public void deleteByRefresh(String refreshToken) {
        redisTemplate.delete(refreshToken);
    }
}
