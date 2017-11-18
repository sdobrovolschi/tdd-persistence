package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Customer;
import com.example.customerservice.domain.model.CustomerId;
import com.example.customerservice.domain.model.CustomerName;
import com.example.customerservice.domain.model.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.customerservice.domain.model.CustomerSpecifications.loyalCustomer;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbUnitConfig.class)
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners = DbUnitTestExecutionListener.class)
public class JpaCustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    private JacksonTester<Customer> json;

    private JacksonTester<List<Customer>> customerListJson;

    @Before
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.addMixIn(CustomerId.class, MixIns.CustomerIdMixIn.class);
        objectMapper.addMixIn(CustomerName.class, MixIns.CustomerNameMixIn.class);

        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @ExpectedDatabase(value = "classpath:db/datasets/customer.xml", assertionMode = NON_STRICT)
    public void shouldPersistACustomer() {
        CustomerId customerId = CustomerId.of(UUID.fromString("5d30c2bf-76d2-4928-9f93-d9149a4594a6"));
        customerRepository.add(new Customer(customerId, CustomerName.of("Customer1")));

        entityManager.flush();
    }

    @Test
    @DatabaseSetup("classpath:db/datasets/customers.xml")
    public void shouldFindCustomerById() throws Exception {
        CustomerId customerId = CustomerId.of(UUID.fromString("397e5247-205f-4011-b372-5b835c15fa9a"));
        Optional<Customer> customer = customerRepository.find(customerId);

        assertThat(customer)
                .usingFieldByFieldValueComparator()
                .contains(json.readObject(new ClassPathResource("customer.json")));
    }

    @Test
    @DatabaseSetup("classpath:db/datasets/customers.xml")
    public void shouldFindAllLoyalCustomers() throws Exception {
        List<Customer> loyalCustomers = customerRepository.findAll(loyalCustomer());

        assertThat(loyalCustomers)
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(customerListJson.readObject(new ClassPathResource("loyal-customers.json")));
    }
}
