package com.example.openrewrite.spring5;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomer(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }

    public Customer registerCustomer(String id, String name, String email) {
        return customerRepository.save(new Customer(id, name, email));
    }
}
