package com.company;

import com.company.model.Customer;
import com.company.model.Orders;
import com.company.model.Shoes;
import com.company.model.ShoesOrder;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/com/company/Settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ShoesOrder> getOrderShoesList(int customerid, int orderid) {
        List<ShoesOrder> ordersShoesList = new ArrayList<>();
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             PreparedStatement ps = conn.prepareStatement("select * from ORDERS " +
                     "inner join SHOESORDER on SHOESORDER.orders_id = ORDERS.id " +
                     "inner join SHOES on SHOES.id = SHOESORDER.shoes_id " +
                     "inner join CUSTOMER on ORDERS.customer_id = CUSTOMER.id " +
                     "where customer_id = ? and orders_id = ?;")) {
            ps.setInt(1, customerid);
            ps.setInt(2, orderid);
            rs = ps.executeQuery();
            while (rs.next()) {
                ordersShoesList.add(new ShoesOrder(
                        rs.getInt("ORDERS.id"),
                        rs.getInt("quantity"),
                        new Shoes(rs.getInt("SHOES.id"),
                                rs.getString("label"),
                                rs.getInt("pris"),
                                rs.getInt("size"),
                                rs.getInt("stock"),
                                rs.getString("color")),
                        new Orders(rs.getInt("ORDERS.id"),
                                new Customer(rs.getInt("CUSTOMER.id"),
                                        rs.getString("first_name"),
                                        rs.getString("last_name"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getString("password")),
                                rs.getDate("date"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersShoesList;
    }

    public int getCurrentOrderId() {
        int orderid = 0;
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM orders;")) {
            while (rs.next()) {
                orderid = rs.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderid;
    }

    public int getShoesID(String shoeLabel, String color, String size) {
        int shoeid = 0;
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             PreparedStatement ps = conn.prepareStatement("select * from shoes where label = ? and color = ? " +
                     "and size = ?;")) {
            ps.setString(1, shoeLabel);
            ps.setString(2, color);
            ps.setString(3, size);
            rs = ps.executeQuery();
            while (rs.next()) {
                shoeid = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shoeid;
    }

    public String addToCart(int customerid, int ordersid, int shoesid) {

        ResultSet rs = null;
        String errormessage = "";
        String query = "call addToCart(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, customerid);
            stmt.setInt(2, ordersid);
            stmt.setInt(3, shoesid);
            rs = stmt.executeQuery();
            while (rs != null && rs.next()) {
                errormessage = rs.getString("error");
            }
            if (!errormessage.equals("")) {
                return errormessage;
            }
        } catch (Exception e) {
            System.out.println("e.mess " + e.getMessage());
            return "Could not add to database";
        }
        return "Added to database";
    }

    public List<Shoes> listOfProducts() {
        List<Shoes> shoesList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from shoes;")) {
            while (rs.next()) {
                shoesList.add(new Shoes(rs.getInt("id"),
                        rs.getString("label"),
                        rs.getInt("pris"),
                        rs.getInt("size"),
                        rs.getInt("stock"),
                        rs.getString("color")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoesList;
    }

    public Customer currentCustomer(String username, String password) {
        ResultSet rs = null;
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER " +
                     "where email = ? and password = ?;")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    public boolean isCustomerAuthorizedToLogin(String username, String password) {
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"), p.getProperty("password"));
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER " +
                     "where email = ? and password = ?;")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
