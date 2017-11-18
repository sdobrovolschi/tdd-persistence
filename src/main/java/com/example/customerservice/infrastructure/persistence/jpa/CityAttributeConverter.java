package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.City;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stanislav Dobrovolschi
 */
@Converter(autoApply = true)
public class CityAttributeConverter implements AttributeConverter<City, String> {

    @Override
    public String convertToDatabaseColumn(City attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public City convertToEntityAttribute(String dbData) {
        return dbData == null ? null : City.of(dbData);
    }
}
