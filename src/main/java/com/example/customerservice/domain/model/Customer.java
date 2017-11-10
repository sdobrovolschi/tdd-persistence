package com.example.customerservice.domain.model;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class Customer {

    private CustomerId id;
    private CustomerName name;
    private boolean loyal;

    public Customer(CustomerId id, CustomerName name) {
        Assert.notNull(id, "Id must not be null.");
        Assert.notNull(name, "Name must not be null.");
        this.id = id;
        this.name = name;
    }

    public CustomerId getId() {
        return id;
    }

    public CustomerName getName() {
        return name;
    }

    public boolean isLoyal() {
        return loyal;
    }

    public void markAsLoyal() {
        loyal =  true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
