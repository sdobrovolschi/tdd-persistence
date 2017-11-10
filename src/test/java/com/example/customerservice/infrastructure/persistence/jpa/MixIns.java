package com.example.customerservice.infrastructure.persistence.jpa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * @author Stanislav Dobrovolschi
 */
public interface MixIns {

    abstract class CustomerIdMixIn {

        @JsonCreator
        public CustomerIdMixIn(@JsonProperty("value") UUID id) {

        }
    }

    abstract class CustomerNameMixIn {

        @JsonCreator
        private CustomerNameMixIn(@JsonProperty("name") String name) {

        }
    }
}
