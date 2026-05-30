package com.payflash.controller;


import com.payflash.dto.CustomerRequestDto;
import com.payflash.dto.CustomerResponseDto;
import com.payflash.dto.OrderResponseDto;
import com.payflash.dto.ProductResponseDto;
import com.payflash.model.Customer;
import com.payflash.model.Product;
import com.payflash.service.CustomerService;
import com.payflash.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerContoller {
    private final CustomerService customerService;
    private final ProductService productService;

    CustomerContoller(CustomerService customerService, ProductService productService){
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return  ResponseEntity.ok(customerService.getCustomers(pageable));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto created = customerService.createCustomer(customerRequestDto);
        return  ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CustomerResponseDto>> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getProduct(@PathVariable Long id){
        CustomerResponseDto customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> modifyCustomer(@Valid @PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customer = customerService.modifyCustomer(id, customerRequestDto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDto>> getCustomerOrders(@PathVariable Long id){
        List<OrderResponseDto> orders = customerService.getOrdersByCustomer(id);
        return ResponseEntity.ok(orders);
    }
}
