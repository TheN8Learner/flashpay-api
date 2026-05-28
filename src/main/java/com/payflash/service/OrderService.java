package com.payflash.service;
import com.payflash.exception.OrderNotFoundException;
import com.payflash.model.Order;
import com.payflash.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders(){
        return  orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order createOrder(double totalAmount){
        Order order = new Order(totalAmount);
        return  orderRepository.save(order);
    }

    public Order modifyOrder(Long id ,double totalAmount){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }
}
