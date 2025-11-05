package com.myfirstandroidjava.salesapp.models;

public class ProductItem {
    private int productID;
    private String productName;
    private String briefDescription;
    private double price;
    private String imageURL;
    private String categoryName;
    private String brand;
    private float rating;

    // Getters and setters
    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBriefDescription() { return briefDescription; }
    public void setBriefDescription(String briefDescription) { this.briefDescription = briefDescription; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
