package com.example.eilmarkandroid.ui.cart.model;

import com.example.eilmarkandroid.model.Product;

import java.util.ArrayList;

public class CartItem {
    private final int id;
    private final int productId;
    private int quantity;

    public CartItem(int id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static String getTotalPrice(ArrayList<Product> products, ArrayList<CartItem> items) {
        int price = 0;
        for (int i = 0; i < items.size(); i++) {

        }
        return String.valueOf(price);
    }
}
