package com.ust.book.exception;

public class BookNotFound extends RuntimeException{
    public BookNotFound(String s){
        super(s);
    }
}
