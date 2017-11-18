package com.example.customerservice.infrastructure.persistence.jdbc;

import com.example.customerservice.queries.CustomerEmail;
import com.example.customerservice.queries.CustomerQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("customerQueryService")
public class JdbcCustomerQueryService implements CustomerQueryService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsFor(String name) {
        return jdbcTemplate.queryForObject("select 1 from customer.customer where name = ?", Boolean.class, name);
    }

    @Override
    public Page<CustomerEmail> findAll(Pageable pageable) {
        return null;
    }
}
