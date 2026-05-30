package com.payflash.service;


import com.payflash.dto.OrderRequestDto;
import com.payflash.dto.OrderResponseDto;
import com.payflash.exception.OrderNotFoundException;
import com.payflash.model.Customer;
import com.payflash.model.Order;
import com.payflash.repository.CustomerRepository;
import com.payflash.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private OrderNotFoundException orderNotFoundException;

    @Test
    void shouldCreateOrder() {
        Customer customer = new Customer("Moussa", "Diallo", "moussa@gmail.com");
        OrderRequestDto request = new OrderRequestDto(5000.0, null, 1L);

        Order savedOrder = new Order(5000.0);
        savedOrder.setCustomer(customer); // ✅ customer dans l'ordre sauvegardé

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(orderRepository.save(any())).thenReturn(savedOrder);

        OrderResponseDto result = orderService.createOrder(request);

        assertEquals(5000.0, result.getTotalAmount());
    }

    @Test
    void shouldGetOrderById(){
        Customer customer = new Customer("Moussa", "Diallo", "moussa@gmail.com");
        Order order = new Order(15000);
        order.setCustomer(customer);
        order.setId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        OrderResponseDto result = orderService.getOrderById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldGetOrderByIdException(){
        Customer customer = new Customer("Moussa", "Diallo", "moussa@gmail.com");
        Order order = new Order(15000);
        order.setCustomer(customer);
        order.setId(1L);

        when(orderRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, ()-> orderService.getOrderById(99L));
    }
}