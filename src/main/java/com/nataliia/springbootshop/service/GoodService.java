package com.nataliia.springbootshop.service;

import com.nataliia.springbootshop.model.Good;

import java.util.List;
import java.util.Optional;

public interface GoodService {

    Optional<List<Good>> getAll();

    Optional<Good> getById(Long id);

    void add(Good good);

    void update(Good good);

    void deleteById(Long id);
}

