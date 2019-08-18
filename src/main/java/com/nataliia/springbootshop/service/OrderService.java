package com.nataliia.springbootshop.service;

import com.nataliia.springbootshop.model.Order;

public interface OrderService {

    boolean addOrder(Order order, long userId);
}

