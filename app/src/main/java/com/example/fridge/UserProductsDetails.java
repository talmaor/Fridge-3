package com.example.fridge;

import java.util.ArrayList;

public class UserProductsDetails {
    private int code;
    private String user;
    private ArrayList<ProductDetails> products;

    public String getUser() {
        return user;
    }

    public ArrayList<ProductDetails> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDetails> products) {
        this.products = products;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
