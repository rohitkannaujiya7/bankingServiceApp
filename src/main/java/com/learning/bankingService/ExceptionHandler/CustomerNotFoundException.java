package com.learning.bankingService.ExceptionHandler;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException (String message){
        super(message);
    }
}
