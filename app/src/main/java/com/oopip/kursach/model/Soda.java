package com.oopip.kursach.model;

public class Soda {

    int id;
    String title, price, compound;

    public Soda(int id, String title, String price, String compound) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.compound = compound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompound() {
        return compound;
    }

    public void setCompound(String compound) {
        this.compound = compound;
    }
}
