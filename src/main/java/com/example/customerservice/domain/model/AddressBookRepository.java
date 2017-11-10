package com.example.customerservice.domain.model;

/**
 * @author Stanislav Dobrovolschi
 */
public interface AddressBookRepository {

    void add(AddressBook addressBook);

    AddressBook find(CustomerId customerId);
}
