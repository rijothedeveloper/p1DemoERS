package com.revature.p1demoERS.exception;

public class UserNotFoundException extends  RuntimeException{
    private String message;
    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}