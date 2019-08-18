package com.nataliia.springbootshop.service;

import com.nataliia.springbootshop.model.User;
import com.nataliia.springbootshop.model.UserPayload;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> getAll();

    void add(User user);

    void update(UserPayload user);

    void update(User user);

    Optional<User> getById(Long id);

    Optional<User> getByEmail(String email);

    void deleteById(Long id);
}
