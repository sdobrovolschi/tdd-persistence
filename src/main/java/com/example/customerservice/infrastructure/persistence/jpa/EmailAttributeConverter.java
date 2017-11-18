package com.example.customerservice.infrastructure.persistence.jpa;

import com.example.customerservice.domain.model.Email;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Stanislav Dobrovolschi
 */
@Converter(autoApply = true)
public class EmailAttributeConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Email.of(dbData);
    }
}
