package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.CustomerName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stanislav Dobrovolschi
 */
@Converter(autoApply = true)
public class CustomerNameAttributeConverter implements AttributeConverter<CustomerName, String> {

    @Override
    public String convertToDatabaseColumn(CustomerName attribute) {
        return attribute.toString();
    }

    @Override
    public CustomerName convertToEntityAttribute(String dbData) {
        return CustomerName.of(dbData);
    }
}
