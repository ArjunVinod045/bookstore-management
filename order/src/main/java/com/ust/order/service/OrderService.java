package com.ust.order.service;

import com.ust.order.exception.InvalidOrder;
import com.ust.order.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllOrders();
    Optional<Order> findOrderById(long id);
    Order placeOrder(Order order) throws InvalidOrder;
    Order modifyOrder(Order order,long id);
    void deleteOrder(Order order);
}
