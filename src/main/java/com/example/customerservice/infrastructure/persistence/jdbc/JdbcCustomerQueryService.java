package com.example.customerservice.infrastructure.persistence.jdbc;

import com.example.customerservice.queries.CustomerEmail;
import com.example.customerservice.queries.CustomerQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("customerQueryService")
public class JdbcCustomerQueryService implements CustomerQueryService {

    @Override
    public boolean existsFor(String name) {
        return false;
    }

    @Override
    public Page<CustomerEmail> findAll(Pageable pageable) {
        return null;
    }
}
