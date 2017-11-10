package com.example.customerservice.domain.model;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class Email {

    private final String value;

    private Email(String value) {
        Assert.hasText(value, "Value must not be empty.");
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
