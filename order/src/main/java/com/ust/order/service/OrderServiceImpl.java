package com.ust.order.service;

import com.ust.order.exception.InvalidOrder;
import com.ust.order.feignClient.BookClient;
import com.ust.order.feignClient.CustomerClient;
import com.ust.order.model.Order;
import com.ust.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookClient bookClient;
    @Autowired
    CustomerClient customerClient;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order placeOrder(Order order) throws InvalidOrder{
        long quantity = validateOrder(order);
        bookClient.updateBook(order.getBookId(),quantity);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order modifyOrder(Order order,long id) {
        return orderRepository.save(Order.builder()
                        .id(id)
                        .bookId(order.getBookId())
                        .customerId(order.getCustomerId())
                        .quantity(order.getQuantity())
                        .status(order.getStatus())
                        .build());
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    long validateOrder(Order order) throws InvalidOrder {
        var book = bookClient.getBookById(order.getBookId()).orElseThrow(() -> new InvalidOrder("No such book exists in the warehouse"));
        var customer = customerClient.getCustomerById(order.getCustomerId()).orElseThrow(() -> new InvalidOrder("No such customer found registered"));
        if(book.stock() < order.getQuantity()){
            throw new InvalidOrder("Not enough stock to place order");
        }
        return book.stock()-order.getQuantity();
    }
}
