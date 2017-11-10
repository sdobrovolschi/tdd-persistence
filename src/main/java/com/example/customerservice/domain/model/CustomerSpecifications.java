package com.example.customerservice.domain.model;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Stanislav Dobrovolschi
 */
public final class CustomerSpecifications {

    private CustomerSpecifications() {
    }

    public static Specification<Customer> loyalCustomer() {
        return (root, query, cb) -> cb.equal(root.get("loyal"), true);
    }
}
