package com.example.fridge;

import java.util.Comparator;

public class ProductDetails {
    private String name;
    //Date expiryDate;
    private String exp;
    private String type;

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return exp;
    }

    public void setExpiryDate(String date) {
        exp = date;
    }

    public String getProductType() {
        return type;
    }

    public void setProductType(String type) {
        this.type = type;
    }

    public static Comparator<ProductDetails> ProductNameComparator = new Comparator<ProductDetails>() {

        public int compare(ProductDetails p1, ProductDetails p2) {
            String firstProductName = p1.getProductName().toUpperCase();
            String secondProductName = p2.getProductName().toUpperCase();

            //ascending order
            return firstProductName.compareTo(secondProductName);
        }};

    public static Comparator<ProductDetails> ProductExpiryDateComparator = new Comparator<ProductDetails>() {

        public int compare(ProductDetails p1, ProductDetails p2) {

            //ascending order
            return p1.getExpiryDate().compareTo(p2.getExpiryDate());
        }};

    public static Comparator<ProductDetails> ProductTypeComparator = new Comparator<ProductDetails>() {

        public int compare(ProductDetails p1, ProductDetails p2) {
            String firstProductType= p1.getProductType().toUpperCase();
            String secondProductType = p2.getProductType().toUpperCase();

            //ascending order
            return firstProductType.compareTo(secondProductType);
        }};

}