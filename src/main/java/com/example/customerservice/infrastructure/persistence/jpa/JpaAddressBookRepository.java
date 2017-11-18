package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.AddressBook;
import com.example.customerservice.domain.model.AddressBookRepository;
import com.example.customerservice.domain.model.CustomerId;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("addressBookRepository")
public class JpaAddressBookRepository implements AddressBookRepository {

    private final SimpleJpaRepository<AddressBook, CustomerId> jpaRepository;

    public JpaAddressBookRepository(EntityManager entityManager) {
        this.jpaRepository = new SimpleJpaRepository<>(AddressBook.class, entityManager);
    }

    @Override
    public void add(AddressBook addressBook) {
        jpaRepository.save(addressBook);
    }

    @Override
    public AddressBook find(CustomerId customerId) {
        return null;
    }
}
