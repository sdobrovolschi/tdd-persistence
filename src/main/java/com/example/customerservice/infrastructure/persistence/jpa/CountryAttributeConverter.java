package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stanislav Dobrovolschi
 */
@Converter(autoApply = true)
public class CountryAttributeConverter implements AttributeConverter<Country, String> {

    @Override
    public String convertToDatabaseColumn(Country attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Country convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Country.of(dbData);
    }
}
