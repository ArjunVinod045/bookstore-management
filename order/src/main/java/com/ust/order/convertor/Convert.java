package com.ust.order.convertor;

import com.ust.order.dto.OrderDto;
import com.ust.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convert {

    public Order toEntity(OrderDto orderDto){
        return Order.builder()
                .customerId(orderDto.customerId())
                .bookId(orderDto.bookId())
                .quantity(orderDto.quantity())
                .status(orderDto.status())
                .build();
    }

    public OrderDto toDto(Order order){
        return new OrderDto(order.getCustomerId(),order.getBookId(),order.getQuantity(),order.getStatus());
    }

    public List<OrderDto> toDto(List<Order> orders){
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        for(Order order: orders){
            orderDtos.add(toDto(order));
        }
        return orderDtos;
    }
}
