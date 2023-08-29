package com.dev.productservice.handler;

public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
