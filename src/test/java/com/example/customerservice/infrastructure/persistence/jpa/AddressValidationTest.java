package com.example.customerservice.infrastructure.persistence.jpa;

import com.github.constraint.maintainer.DisablingMode;
import com.github.constraint.maintainer.integration.spring.EnableConstraintMaintainer;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DbUnitConfig.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = DbUnitTestExecutionListener.class)
@EnableConstraintMaintainer(DisablingMode.REFERENTIAL)
@TestPropertySource(properties = "constraint.maintainer.default-schema=customer")
public class AddressValidationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DatabaseSetup("classpath:db/datasets/address-book.xml")
    public void insertShouldFailWhenDuplicatePhoneNumber() {
        String queryString = "insert into customer.address_book (customer_id, country, city, email, phone_number)" +
                " values (?, ?, ?, ?, ?)";

        assertThatThrownBy(() -> jdbcTemplate.update(queryString,
                "fab3bf26-93b3-439e-8034-13ad8ee0e3e2",
                "Republic of Moldova",
                "Chisinau",
                "customer2@gmail.com",
                "+37379111111"))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasRootCauseExactlyInstanceOf(SQLServerException.class)
                .hasStackTraceContaining("UQ_ADDRESS_BOOK_PHONE_NUMBER");
    }
}
