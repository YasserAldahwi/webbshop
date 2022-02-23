package com.company.model;

import java.util.List;

public class Shoes {
    private int id;
    private String label;
    private int price;
    private int size;
    private int stock;
    private String color;
    private List<ShoesOrder> shoesOrderList;
    private List<Category> categoryList;

    public Shoes(int id, String label, int price, int size, int stock, String color) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public int getStock() {
        return stock;
    }

    public String getColor() {
        return color;
    }

    public List<ShoesOrder> getShoesOrderList() {
        return shoesOrderList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void printShoes(){
        System.out.println("Label: " + label + " - Size: " + size + " - Color: " + color + " - Price: " + price);
    }
}