package com.example.customerservice.domain.model;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class AddressBook {

    private CustomerId customerId;
    private Address address;

    public AddressBook(CustomerId customerId, Address address) {
        this.customerId = customerId;
        this.address = address;
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
}
