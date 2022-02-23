package com.company.model;

import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private String password;
    private List<Orders> ordersList;

    public Customer(int id, String firstName, String lastName, String address, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }
}
