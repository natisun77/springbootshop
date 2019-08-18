package com.nataliia.springbootshop.repository;

import com.nataliia.springbootshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
