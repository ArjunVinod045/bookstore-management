package com.ust.order.exception;

public class InvalidOrder extends RuntimeException{
    public InvalidOrder(String s){
        super(s);
    }
}
