package com.example.customerservice.domain.model;

import org.hibernate.annotations.Type;
import org.springframework.util.Assert;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * @author Stanislav Dobrovolschi
 */
public class CustomerId implements Serializable {

    @Type(type = "uuid-char")
    @Column(name = "ID")
    private final UUID value;

    private CustomerId(UUID value) {
        Assert.notNull(value, "Value must not be null.");
        this.value = value;
    }

    public static CustomerId of(UUID id) {
        return new CustomerId(id);
    }

    public static CustomerId nextIdentity() {
        return of(randomUUID());
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerId that = (CustomerId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
