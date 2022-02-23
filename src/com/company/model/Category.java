package com.company.model;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Shoes> shoesList;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }
}
