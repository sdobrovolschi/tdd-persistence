package com.example.customerservice.domain.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(schema = "customer", name = "ADDRESS_BOOK")
public class AddressBook implements Persistable<CustomerId> {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "CUSTOMER_ID"))
    private CustomerId customerId;

    @Embedded
    private Address address;

    @Transient
    private boolean isNew;

    public AddressBook(CustomerId customerId, Address address) {
        this.customerId = customerId;
        this.address = address;

        isNew = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressBook that = (AddressBook) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId);
    }

    @Override
    public CustomerId getId() {
        return customerId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
