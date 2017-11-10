package com.example.customerservice.domain.model;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
public class Address {

    private final Country country;
    private final City city;
    private final Email email;
    private final PhoneNumber phoneNumber;

    private Address(Country country, City city, Email email, PhoneNumber phoneNumber) {
        Assert.notNull(country, "Country must not be null.");
        Assert.notNull(city, "City must not be null.");
        Assert.notNull(email, "Email must not be null.");
        Assert.notNull(phoneNumber, "PhoneNumber must not be null.");
        this.country = country;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(email, address.email) &&
                Objects.equals(phoneNumber, address.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, email, phoneNumber);
    }

    public static class Builder {

        private Country country;
        private City city;
        private Email email;
        private PhoneNumber phoneNumber;

        public Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public Builder setCity(City city) {
            this.city = city;
            return this;
        }

        public Builder setEmail(Email email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Address createAddress() {
            return new Address(country, city, email, phoneNumber);
        }
    }
}
