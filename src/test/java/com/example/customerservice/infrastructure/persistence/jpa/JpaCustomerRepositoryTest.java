package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.model.CustomerId;
import com.example.customerservice.domain.model.CustomerName;
import com.example.customerservice.domain.model.CustomerRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbUnitConfig.class)
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners = DbUnitTestExecutionListener.class)
public class JpaCustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @ExpectedDatabase(value = "classpath:db/datasets/customer.xml", assertionMode = NON_STRICT)
    public void shouldPersistACustomer() {
        CustomerId customerId = CustomerId.of(UUID.fromString("5d30c2bf-76d2-4928-9f93-d9149a4594a6"));
        customerRepository.add(new Customer(customerId, CustomerName.of("Customer1")));

        entityManager.flush();
    }
}
