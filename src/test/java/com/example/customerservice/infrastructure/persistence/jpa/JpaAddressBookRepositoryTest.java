package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Address;
import com.example.customerservice.domain.model.AddressBook;
import com.example.customerservice.domain.model.AddressBookRepository;
import com.example.customerservice.domain.model.City;
import com.example.customerservice.domain.model.Country;
import com.example.customerservice.domain.model.CustomerId;
import com.example.customerservice.domain.model.Email;
import com.example.customerservice.domain.model.PhoneNumber;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbUnitConfig.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = DbUnitTestExecutionListener.class)
public class JpaAddressBookRepositoryTest {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @ExpectedDatabase(value = "classpath:db/datasets/address-book.xml", assertionMode = NON_STRICT)
    public void shouldPersistAnAddressBook() {
        CustomerId customerId = CustomerId.of(UUID.fromString("5d30c2bf-76d2-4928-9f93-d9149a4594a6"));
        Address address = new Address.Builder()
                .setCountry(Country.of("Republic of Moldova"))
                .setCity(City.of("Chisinau"))
                .setEmail(Email.of("customer1@gmail.com"))
                .setPhoneNumber(PhoneNumber.of("+37379111111"))
                .createAddress();

        AddressBook addressBook = new AddressBook(customerId, address);

        addressBookRepository.add(addressBook);

        entityManager.flush();
    }
}
