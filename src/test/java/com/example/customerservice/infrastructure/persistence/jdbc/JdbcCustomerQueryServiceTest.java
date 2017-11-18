package com.example.customerservice.infrastructure.persistence.jdbc;

import com.example.customerservice.infrastructure.persistence.jpa.DbUnitConfig;
import com.example.customerservice.queries.CustomerEmail;
import com.example.customerservice.queries.CustomerQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.constraint.maintainer.DisablingMode;
import com.github.constraint.maintainer.integration.spring.EnableConstraintMaintainer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbUnitConfig.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = DbUnitTestExecutionListener.class)
@EnableConstraintMaintainer(DisablingMode.ALL)
@TestPropertySource(properties = "constraint.maintainer.default-schema=customer")
public class JdbcCustomerQueryServiceTest {

    @Autowired
    private CustomerQueryService customerQueryService;

    private JacksonTester<List<CustomerEmail>> customerEmailListJson;

    @Before
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.addMixIn(CustomerEmail.class, MixIns.CustomerEmailMixIn.class);

        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DatabaseSetup("classpath:db/datasets/customer-by-name.xml")
    public void shouldCheckWhenCustomerExistsByName() {
        boolean customerExists = customerQueryService.existsFor("Customer1");

        assertThat(customerExists).isTrue();
    }

    @Test
    @DatabaseSetup("classpath:db/datasets/customers-with-emails.xml")
    public void shouldFindAllCustomerEmails() throws Exception {
        Page<CustomerEmail> customers = customerQueryService.findAll(new PageRequest(0, 3));

        List<CustomerEmail> expectedCustomers = customerEmailListJson.readObject(new ClassPathResource("customers-with-emails.json"));

        assertSoftly(softly -> {
            softly.assertThat(customers.getTotalElements()).isEqualTo(5);
            softly.assertThat(customers.getContent())
                    .usingFieldByFieldElementComparator()
                    .containsExactlyElementsOf(expectedCustomers);
        });
    }
}
