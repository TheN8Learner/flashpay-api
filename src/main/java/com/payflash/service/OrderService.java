package com.payflash.service;
import com.payflash.dto.OrderRequestDto;
import com.payflash.dto.OrderResponseDto;
import com.payflash.exception.CustomerNotFoundException;
import com.payflash.exception.OrderNotFoundException;
import com.payflash.model.Customer;
import com.payflash.model.Order;
import com.payflash.repository.CustomerRepository;
import com.payflash.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;


    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Page<OrderResponseDto> getOrders(Pageable pageable){
        return orderRepository.findAll(pageable)
                .map(order -> new OrderResponseDto(
                        order.getId(),
                        order.getTotalAmount(),
                        order.isPaid(),
                        order.getCustomer() != null ? order.getCustomer().getId() : null
                ));    }

    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return new OrderResponseDto(order.getId(), order.getTotalAmount(), order.isPaid(), order.getCustomer().getId()) ;
    }

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(requestDto.getCustomerId()));

        Order order = new Order(requestDto.getTotalAmount());
        order.setCustomer(customer);
        Order saved = orderRepository.save(order);
        return new OrderResponseDto(saved.getId(), saved.getTotalAmount(), saved.isPaid(), saved.getCustomer().getId());
    }

    public OrderResponseDto modifyOrder(Long id ,OrderRequestDto requestDto){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setTotalAmount(requestDto.getTotalAmount());
        Order saved = orderRepository.save(order);
        return new OrderResponseDto(saved.getId(), saved.getTotalAmount(), saved.isPaid(), saved.getCustomer().getId());
    }

    public void deleteOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }
}
