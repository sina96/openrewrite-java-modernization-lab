package com.example.openrewrite.spring5;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(String id);

    Customer save(Customer customer);
}
