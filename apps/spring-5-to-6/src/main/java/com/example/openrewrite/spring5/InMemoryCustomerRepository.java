package com.example.openrewrite.spring5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> customers = new LinkedHashMap<>();

    public InMemoryCustomerRepository() {
        save(new Customer("C-1001", "Ada Lovelace", "ada@example.com"));
        save(new Customer("C-1002", "Grace Hopper", "grace@example.com"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }
}
