package com.ust.customer.exception;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound(String s){
        super(s);
    }
}
