package com.app.geniegiftapp;

import com.google.gson.annotations.SerializedName;

public class Gift {
    @SerializedName("title")
    private String name;
    private double price; // Not in API, we'll fake it
    @SerializedName("url")
    private String imageUrl;

    public Gift(String name, double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}