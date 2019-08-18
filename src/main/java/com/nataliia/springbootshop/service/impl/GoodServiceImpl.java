package com.nataliia.springbootshop.service.impl;

import com.nataliia.springbootshop.model.Good;
import com.nataliia.springbootshop.repository.GoodJpaRepository;
import com.nataliia.springbootshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {

    private final GoodJpaRepository goodJpaRepository;

    @Autowired
    public GoodServiceImpl(GoodJpaRepository goodJpaRepository) {
        this.goodJpaRepository = goodJpaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Good>> getAll() {
        return Optional.ofNullable(goodJpaRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Good> getById(Long id) {
        return goodJpaRepository.findById(id);
    }

    @Transactional
    @Override
    public void add(Good good) {
        goodJpaRepository.save(good);
    }

    @Transactional
    @Override
    public void update(Good good) {
        goodJpaRepository.save(good);
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        goodJpaRepository.deleteById(id);
    }
}
