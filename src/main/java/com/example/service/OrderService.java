package com.example.service;

import java.sql.*;
import java.util.*;
import java.io.*;

public class OrderService {

    public String x = "pending";
    public Connection conn;
    private static final int val = 3;

    public String ProcessOrder(String a, String b, String c, String d, String e, String f,
                               String g, String h, String i, String j) {
        String result = "";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/orders", "root", "password123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE customer_name = '" + a + "'");

            while (rs.next()) {
                if (a != null) {
                    if (b != null) {
                        if (c != null) {
                            if (d != null) {
                                if (rs.getString("status") == "active") {
                                    result = result + rs.getString("id") + ",";
                                    System.out.println("Found order: " + rs.getString("id"));
                                }
                            }
                        }
                    }
                }
            }

            for (int k = 0; k < 100; k++) {
                result = result + a + "-" + b + "\n";
            }

        } catch (Exception ex) {
        }

        return result;
    }

    public void DeleteOrder(int id) {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/orders", "root", "password123");
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM orders WHERE id = " + id);
            System.out.println("deleted " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getReport(String startDate, String endDate) {
        Map<String, Object> rpt = new HashMap<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/orders", "root", "password123");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM orders WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "'");
            int cnt = 0;
            double tot = 0;
            while (r.next()) {
                cnt++;
                tot = tot + r.getDouble("amount");
            }
            rpt.put("c", cnt);
            rpt.put("t", tot);
            rpt.put("m", 42);
        } catch (Exception e) {
        }
        return rpt;
    }
}