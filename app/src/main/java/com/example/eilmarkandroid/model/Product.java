package com.example.eilmarkandroid.model;

import java.util.ArrayList;

public class Product {
    int id;
    String title;
    String sub_title = "";
    int price;

    public Product(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    private static int lastContactId = 0;

    public static ArrayList<Product> createProductsList(int numContacts) {
        ArrayList<Product> contacts = new ArrayList<Product>();

        for (int i = 1; i <= numContacts; i++) {
            Product product = new Product(i, "محصول " + ++lastContactId, i * 1000);
            product.setSub_title("گرمی" + i * 100);
            contacts.add(product);
        }

        return contacts;
    }
}
