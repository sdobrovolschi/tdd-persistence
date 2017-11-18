package com.example.customerservice.infrastructure.persistence.jdbc;

import com.example.customerservice.queries.CustomerEmail;
import com.example.customerservice.queries.CustomerQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String queryString = "select c.name, ab.email" +
                " from customer.customer c" +
                "   join customer.address_book ab on c.id = ab.customer_id" +
                " order by c.name" +
                " offset ? rows fetch next ? rows only";

        List<CustomerEmail> customers = jdbcTemplate.query(queryString,
                (rs, rowNum) -> new CustomerEmail(rs.getString("name"), rs.getString("email")),
                pageable.getOffset(), pageable.getPageSize());

        return PageableExecutionUtils.getPage(customers, pageable,
                () -> jdbcTemplate.queryForObject("select count(1) from customer.customer", Long.class));
    }
}
