package com.payflash.controller;
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
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order){
        Order created = orderService.createOrder(order.getTotalAmount());
        return ResponseEntity.status(201).body(created);
    }

    //Ceci est un commentaire
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        Order order =  orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> modifyOrder(@RequestBody Order order ,@PathVariable Long id){
        Order theorder = orderService.modifyOrder(id, order.getTotalAmount());
        return  ResponseEntity.ok(theorder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Order>> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return  ResponseEntity.noContent().build();
    }
}

