package com.revature.p1demoERS.exception;

public class ReimbNotFoundException extends  RuntimeException{
    private String message;
    public ReimbNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
