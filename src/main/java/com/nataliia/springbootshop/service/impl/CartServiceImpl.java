package com.nataliia.springbootshop.service.impl;

import com.nataliia.springbootshop.model.Cart;
import com.nataliia.springbootshop.model.Good;
import com.nataliia.springbootshop.repository.CartJpaRepository;
import com.nataliia.springbootshop.repository.GoodJpaRepository;
import com.nataliia.springbootshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartJpaRepository cartJpaRepository;
    private final GoodJpaRepository goodJpaRepository;

    @Autowired
    public CartServiceImpl(CartJpaRepository cartJpaRepository, GoodJpaRepository goodJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.goodJpaRepository = goodJpaRepository;
    }

    @Transactional
    @Override
    public boolean addGoodToCart(long cartId, long goodId) {
        Optional<Cart> optionalCart = cartJpaRepository.findById(cartId);

        if (!optionalCart.isPresent()) {
            return false;
        }
        Cart cart = optionalCart.get();
        Good good = new Good();
        good.setId(goodId);
        cart.addGood(good);
        cartJpaRepository.save(cart);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Good>> getAll(long cartId) {
        return Optional.of(cartJpaRepository.loadAllGoodsFromCart(cartId));
    }

    @Transactional
    @Override
    public void deleteGoodFromCart(long cartId, long goodId) {
        Optional<Cart> optionalCart = cartJpaRepository.findById(cartId);
        Optional<Good> optionalGood = goodJpaRepository.findById(goodId);
        if (optionalCart.isPresent() && optionalGood.isPresent()) {
            Cart cart = optionalCart.get();
            Good good = optionalGood.get();

            cart.deleteGood(good);
            cartJpaRepository.save(cart);
        }
    }

    @Transactional
    @Override
    public void cleanAll(long cartId) {
        Optional<Cart> optionalCart = cartJpaRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.resetCart();
            cartJpaRepository.save(cart);
        }
    }
}
