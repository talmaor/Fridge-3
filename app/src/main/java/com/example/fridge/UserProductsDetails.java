package com.example.fridge;

import java.util.ArrayList;

public class UserProductsDetails {
    private int code;
    private String name;
    private ArrayList<ProductDetails> products;
    private String userID;
    private String groupID;

    public String getUser() {
        return name;
    }

    public ArrayList<ProductDetails> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDetails> products) {
        this.products = products;
    }

    public void setUser(String user) {

        this.name = user;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
