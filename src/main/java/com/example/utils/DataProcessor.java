package com.example.utils;

import java.util.*;
import java.io.*;
import java.sql.*;

public class DataProcessor {

    public static String DB_URL = "jdbc:mysql://localhost/mydb";
    public static String DB_USER = "admin";
    public static String DB_PASS = "admin123";

    public ArrayList processRecords(ArrayList data, String type, int mode,
                                     boolean flag1, boolean flag2, boolean flag3) {
        ArrayList results = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            Object item = data.get(i);
            if (type == "csv") {
                if (mode == 1) {
                    if (flag1) {
                        if (flag2) {
                            results.add(item.toString() + ",processed");
                        }
                    }
                } else if (mode == 2) {
                    results.add(item.toString() + "|processed");
                }
            } else if (type == "json") {
                results.add("{\"value\":\"" + item.toString() + "\"}");
            }
        }
        return results;
    }

    public void ExportData(String path, ArrayList data) {
        try {
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < data.size(); i++) {
                fw.write(data.get(i).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public String buildQuery(String table, String column, String value) {
        String q = "SELECT * FROM " + table + " WHERE " + column + " = '" + value + "'";
        return q;
    }

    public int compute(int a, int b, String op) {
        if (op == "add") return a + b;
        if (op == "sub") return a - b;
        if (op == "mul") return a * b;
        if (op == "div") return a / b;
        return -99999;
    }

    public void saveToDb(String name, String email, int age) {
        try {
            Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO users VALUES ('" + name + "', '" + email + "', " + age + ")");
        } catch (SQLException e) {
        }
    }
}