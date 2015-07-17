package com.example.fridge;

import java.util.Comparator;
import java.util.Date;

public class ProductDetails {
    private String name;
    //Date expiryDate;
    private Date exp;
    private String type;

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return exp;
    }

    public void setExpiryDate(Date date) {
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
            Date firstExpiryDate = p1.getExpiryDate();
            Date secondExpiryDate = p2.getExpiryDate();

            //ascending order
            return firstExpiryDate.compareTo(secondExpiryDate);
        }};

    public static Comparator<ProductDetails> ProductTypeComparator = new Comparator<ProductDetails>() {

        public int compare(ProductDetails p1, ProductDetails p2) {
            String firstProductType= p1.getProductType().toUpperCase();
            String secondProductType = p2.getProductType().toUpperCase();

            //ascending order
            return firstProductType.compareTo(secondProductType);
        }};

}