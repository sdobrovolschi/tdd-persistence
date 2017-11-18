package com.example.customerservice.infrastructure.persistence.jdbc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Stanislav Dobrovolschi
 */
public interface MixIns {

    abstract class CustomerEmailMixIn {

        @JsonCreator
        public CustomerEmailMixIn(@JsonProperty("name") String name, @JsonProperty("email") String email) {

        }
    }
}
