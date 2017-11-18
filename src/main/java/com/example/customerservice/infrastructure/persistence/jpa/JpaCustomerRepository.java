package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.model.CustomerId;
import com.example.customerservice.domain.model.CustomerRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("customerRepository")
public class JpaCustomerRepository implements CustomerRepository {

    private final SimpleJpaRepository<Customer, CustomerId> jpaRepository;

    public JpaCustomerRepository(EntityManager entityManager) {
        this.jpaRepository = new SimpleJpaRepository<>(Customer.class, entityManager);
    }

    @Override
    public void add(Customer customer) {
        jpaRepository.save(customer);
    }

    @Override
    public Optional<Customer> find(CustomerId id) {
        return Optional.ofNullable(jpaRepository.findOne(id));
    }

    @Override
    public List<Customer> findAll(Specification<Customer> spec) {
        return jpaRepository.findAll(spec);
    }
}
