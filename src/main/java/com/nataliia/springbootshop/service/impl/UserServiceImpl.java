package com.nataliia.springbootshop.service.impl;

import com.nataliia.springbootshop.model.Cart;
import com.nataliia.springbootshop.model.User;
import com.nataliia.springbootshop.model.UserPayload;
import com.nataliia.springbootshop.repository.UserJpaRepository;
import com.nataliia.springbootshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<User>> getAll() {
        return Optional.of(userJpaRepository.findAll());
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setRole("user");
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userJpaRepository.save(user);
    }

    @Override
    @Transactional
    public void update(UserPayload userPayload) {
        Optional<User> userOptional = userJpaRepository.findById(userPayload.getId());
        userOptional.ifPresent((user -> {
            user.setFirstName(userPayload.getFirstName());
            user.setLastName(userPayload.getLastName());
            user.setEmail(userPayload.getEmail());
            user.setPassword(userPayload.getPassword());

            userJpaRepository.save(user);
        }));
    }

    @Override
    @Transactional
    public void update(User user) {
        Optional<User> optionalOfNewUser = userJpaRepository.findById(user.getId());
        optionalOfNewUser.ifPresent((newUser) -> {
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());

            userJpaRepository.save(newUser);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}