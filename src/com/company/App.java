package com.company;

import com.company.model.Customer;
import com.company.model.Shoes;
import com.company.model.ShoesOrder;

import java.util.Scanner;

public class App {
    Repository r = new Repository();
    String email = null;
    String password = null;
    Customer currentCustomer;
    int orderId = 0;
    Scanner sc = new Scanner(System.in);

    public App() {
        // Check if the email and password exists in the database
        while(!r.isCustomerAuthorizedToLogin(email, password)) {
            System.out.println("Enter you email");
            email = sc.nextLine().trim();

            System.out.println("Enter you password");
            password = sc.nextLine().trim();
            r.isCustomerAuthorizedToLogin(email,password);
        }

        currentCustomer = r.currentCustomer(email, password);

        for (Shoes shoes : r.listOfProducts()) {
            shoes.printShoes();
        }

        while(true){
            String label;
            String size;
            String color;

            System.out.println("What shoe do you want to buy");
            System.out.println("q to quit");
            label = sc.nextLine().trim();

            if(label.equalsIgnoreCase("q"))
                break;

            System.out.println("What size do you choose");
            size = sc.nextLine().trim();
            System.out.println("What color do you choose");
            color = sc.nextLine().trim();

            int customerid = currentCustomer.getId();
            int shoeid = r.getShoesID(label, color, size);

            if(orderId == 0){
                System.out.println(r.addToCart(customerid,0, shoeid));
                orderId = r.getCurrentOrderId();
            }else{
                System.out.println(r.addToCart(customerid,orderId,shoeid));
            }
        }
        System.out.println("Your order is: ");
        r.getOrderShoesList(currentCustomer.getId(), orderId).forEach(ShoesOrder::printShoesOrder);
    }
}