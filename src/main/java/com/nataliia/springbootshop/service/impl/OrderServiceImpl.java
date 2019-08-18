package com.nataliia.springbootshop.service.impl;


import com.nataliia.springbootshop.model.Cart;
import com.nataliia.springbootshop.model.Order;
import com.nataliia.springbootshop.model.User;
import com.nataliia.springbootshop.repository.UserJpaRepository;
import com.nataliia.springbootshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public OrderServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public boolean addOrder(Order order, long userId) {
        Optional<User> optionalUser = userJpaRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        Cart cart = user.getCart();

        order.getGoodsInOrder().addAll(cart.getGoodsInCart());

        order.setUser(user);
        user.getOrders().add(order);

        cart.resetCart();
        userJpaRepository.save(user);

        return true;
    }
}
