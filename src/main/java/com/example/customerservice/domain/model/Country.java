package com.example.customerservice.domain.model;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class Country {

    private final String name;

    private Country(String name) {
        Assert.hasText(name, "Name must not be empty.");
        this.name = name;
    }

    public static Country of(String name) {
        return new Country(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(name, country.name);
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
