package com.nataliia.springbootshop.service;

import com.nataliia.springbootshop.model.Good;

import java.util.List;
import java.util.Optional;

public interface CartService {

    boolean addGoodToCart(long cartId, long goodId);

    Optional<List<Good>> getAll(long cartId);

    void deleteGoodFromCart(long cartId, long goodId);

    void cleanAll(long cartId);
}
