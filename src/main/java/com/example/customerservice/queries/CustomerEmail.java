package com.example.customerservice.queries;

/**
 * @author Stanislav Dobrovolschi
 */
public class CustomerEmail {

    public final String customerName;
    public final String email;

    public CustomerEmail(String customerName, String email) {
        this.customerName = customerName;
        this.email = email;
    }
}
