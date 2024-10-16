package com.petweb.sponge.user.mock;

import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.service.port.UserRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MockUserRepository implements UserRepository {
    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<User> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<User> findById(Long id) {
        return data.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            User newUser = User.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
            data.add(newUser);
            return newUser;
        }
        else {
            data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
            data.add(user);
            return user;
        }

    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public void deleteAddress(Long id) {

    }

    @Override
    public void delete(User user) {
        data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
    }
}
