package com.payflash.exception;

public class OrderNotFoundException extends  RuntimeException{
    public OrderNotFoundException(Long id){
        super("Order not Found " + id);
    }
}
