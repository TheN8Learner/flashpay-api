package com.payflash.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer " + id + " Not Found");
    }
}
