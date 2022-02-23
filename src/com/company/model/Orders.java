package com.company.model;

import java.util.Date;
import java.util.List;

public class Orders {
    private int id;
    private Customer customer;
    private Date date;
    private List<ShoesOrder> shoesOrderList;

    public Orders(int id, Customer customer, Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public List<ShoesOrder> getShoesOrderList() {
        return shoesOrderList;
    }
}