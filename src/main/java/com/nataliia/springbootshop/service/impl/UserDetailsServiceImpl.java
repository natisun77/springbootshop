package com.nataliia.springbootshop.service.impl;

import com.nataliia.springbootshop.model.User;
import com.nataliia.springbootshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = findUserByUsername(userName);

        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private Optional<User> findUserByUsername(String username) {
        return userService.getByEmail(username);
    }

}
