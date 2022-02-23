package com.company.model;

public class ShoesOrder {
    private int id;
    private int quantity;
    private Shoes shoe;
    private Orders order;

    public ShoesOrder(int id, int quantity, Shoes shoe, Orders order) {
        this.id = id;
        this.quantity = quantity;
        this.shoe = shoe;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Shoes getShoe() {
        return shoe;
    }

    public Orders getOrder() {
        return order;
    }

    public void printShoesOrder(){
        System.out.println("Label: " + shoe.getLabel() + " - Color: " + shoe.getColor() + " - Price: " + shoe.getPrice() +
                " - Quantity: " + quantity + " - Date: " + order.getDate());
    }
}
