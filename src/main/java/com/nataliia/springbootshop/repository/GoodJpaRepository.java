package com.nataliia.springbootshop.repository;

import com.nataliia.springbootshop.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodJpaRepository extends JpaRepository<Good, Long> {

}
