package com.example.customerservice.domain.model;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Dobrovolschi
 */
public interface CustomerRepository {

    void add(Customer customer);

    Optional<Customer> find(CustomerId id);

    List<Customer> findAll(Specification<Customer> spec);
}
