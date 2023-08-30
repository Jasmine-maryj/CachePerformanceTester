package com.dev.orderservice.handler;

public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
