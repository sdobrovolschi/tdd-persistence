package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.model.CustomerId;
import com.example.customerservice.domain.model.CustomerRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("customerRepository")
public class JpaCustomerRepository implements CustomerRepository {

    @Override
    public void add(Customer customer) {

    }

    @Override
    public Optional<Customer> find(CustomerId id) {
        return null;
    }

    @Override
    public List<Customer> findAll(Specification<Customer> spec) {
        return null;
    }
}
