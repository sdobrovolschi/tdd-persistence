package com.example.customerservice.infrastructure.persistence.jdbc;

import com.example.customerservice.infrastructure.persistence.jpa.DbUnitConfig;
import com.example.customerservice.queries.CustomerQueryService;
import com.github.constraint.maintainer.DisablingMode;
import com.github.constraint.maintainer.integration.spring.EnableConstraintMaintainer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DatabaseSetup("classpath:db/datasets/customer-by-name.xml")
    public void shouldCheckWhenCustomerExistsByName() {
        boolean customerExists = customerQueryService.existsFor("Customer1");

        assertThat(customerExists).isTrue();
    }
}
