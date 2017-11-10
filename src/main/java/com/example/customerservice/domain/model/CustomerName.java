package com.example.customerservice.domain.model;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class CustomerName {

    private final String name;

    private CustomerName(String name) {
        Assert.hasText(name, "Name must not be empty.");
        this.name = name;
    }

    public static CustomerName of(String name) {
        return new CustomerName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerName that = (CustomerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
