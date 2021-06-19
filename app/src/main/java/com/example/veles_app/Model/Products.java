package com.example.veles_app.Model;

public class Products {
    private String productName, description, price, imageURL;


    public Products() {

    }

    public Products(String productName, String description, String price, String imageURL) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
