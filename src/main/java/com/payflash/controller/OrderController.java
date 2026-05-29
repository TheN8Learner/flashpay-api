package com.payflash.controller;
import com.payflash.dto.OrderRequestDto;
import com.payflash.dto.OrderResponseDto;
import com.payflash.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import  com.payflash.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto requestDto){
        OrderResponseDto created = orderService.createOrder(requestDto);
        return ResponseEntity.status(201).body(created);
    }

    //Ceci est un commentaire
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id ){
        OrderResponseDto order =  orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> modifyOrder(@RequestBody OrderRequestDto requestDto ,@PathVariable Long id){
        OrderResponseDto order = orderService.modifyOrder(id, requestDto);
        return  ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return null;
    }
}

