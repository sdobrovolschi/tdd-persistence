package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.AddressBook;
import com.example.customerservice.domain.model.AddressBookRepository;
import com.example.customerservice.domain.model.CustomerId;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("addressBookRepository")
public class JpaAddressBookRepository implements AddressBookRepository {

    @Override
    public void add(AddressBook addressBook) {

    }

    @Override
    public AddressBook find(CustomerId customerId) {
        return null;
    }
}
