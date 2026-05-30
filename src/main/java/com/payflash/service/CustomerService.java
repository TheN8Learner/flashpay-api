package com.payflash.service;

import com.payflash.dto.CustomerRequestDto;
import com.payflash.dto.CustomerResponseDto;
import com.payflash.dto.OrderResponseDto;
import com.payflash.exception.CustomerNotFoundException;
import com.payflash.model.Customer;
import com.payflash.model.Order;
import com.payflash.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Page<CustomerResponseDto> getCustomers(Pageable pageable){
        return customerRepository.findAll(pageable)
                .map(customer -> new CustomerResponseDto(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                ));
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto){
        Customer created = new Customer(customerRequestDto.getFirstName(), customerRequestDto.getLastName(), customerRequestDto.getEmail());
        CustomerResponseDto customerResponseDto = new CustomerResponseDto(
                created.getId(), created.getFirstName(), created.getLastName(), created.getEmail());
        customerRepository.save(created);
        return customerResponseDto;
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );
        customerRepository.deleteById(id);
    }

    public CustomerResponseDto getCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );
        return  new CustomerResponseDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    public CustomerResponseDto modifyCustomer(Long id, CustomerRequestDto customerRequestDto){
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );
        customer.setFirstName(customerRequestDto.getFirstName());
        customer.setLastName(customerRequestDto.getLastName());
        customer.setEmail(customerRequestDto.getEmail());
        Customer saved = customerRepository.save(customer);
        return new CustomerResponseDto(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail());
    }

    public List<OrderResponseDto> getOrdersByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        List<OrderResponseDto> result = new ArrayList<>();
        for (Order order : customer.getOrders()) {
            result.add(new OrderResponseDto(
                    order.getId(),
                    order.getTotalAmount(),
                    order.isPaid(),
                    order.getCustomer().getId()
            ));
        }
        return result;
    }
}
