package com.ust.order.controller;

import com.ust.order.convertor.Convert;
import com.ust.order.dto.OrderDto;
import com.ust.order.exception.OrderNotFound;
import com.ust.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    Convert convert;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<>(convert.toDto(orderService.findAllOrders()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable long id){
        return new ResponseEntity<>(convert.toDto(orderService.findOrderById(id).orElseThrow(() -> new OrderNotFound("No such order found"))),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderDto orderDto){
        return new ResponseEntity<>(convert.toDto(orderService.placeOrder(convert.toEntity(orderDto))),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable long id,@Valid @RequestBody OrderDto orderDto){
        var orderFetched = orderService.findOrderById(id).orElseThrow(() -> new OrderNotFound("No such order exists inorder to update"));
        return new ResponseEntity<>(convert.toDto(orderService.modifyOrder(convert.toEntity(orderDto),id)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id){
        var orderFetched = orderService.findOrderById(id).orElseThrow(() -> new OrderNotFound("No such order exists inorder to delete"));
        orderService.deleteOrder(orderFetched);
        return new ResponseEntity<>("Order Deleted Successfully",HttpStatus.OK);
    }

}
