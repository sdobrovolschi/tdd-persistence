package com.example.customerservice.domain.model;

import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(schema = "customer", name = "CUSTOMER")
public class Customer implements Persistable<CustomerId> {

    @EmbeddedId
    private CustomerId id;

    @Column(name = "NAME")
    private CustomerName name;

    @Column(name = "IS_LOYAL", insertable = false)
    private boolean loyal;

    @Transient
    private boolean isNew;

    private Customer() {
        // for JPA
    }

    public Customer(CustomerId id, CustomerName name) {
        Assert.notNull(id, "Id must not be null.");
        Assert.notNull(name, "Name must not be null.");
        this.id = id;
        this.name = name;

        isNew = true;
    }

    public CustomerId getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
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
