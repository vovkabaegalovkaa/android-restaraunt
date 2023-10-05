package com.oopip.kursach.model;

public class Product {

    String productName, productPrice;
    int productKolvo;

    public Product(String productName, String productPrice, int productKolvo) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productKolvo = productKolvo;
    }

    public Product(){}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductKolvo() {
        return productKolvo;
    }

    public void setProductKolvo(int productKolvo) {
        this.productKolvo = productKolvo;
    }
}
