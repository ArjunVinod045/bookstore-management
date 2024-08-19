package com.ust.order.exception;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(String s){
        super(s);
    }
}
