package com.example.customerservice.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Stanislav Dobrovolschi
 */
public interface CustomerQueryService {

    boolean existsFor(String name);

    Page<CustomerEmail> findAll(Pageable pageable);
}
