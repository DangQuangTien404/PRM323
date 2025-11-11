package com.myfirstandroidjava.salesapp.models;

public class CartItem {
    private int productId;
    private String productName;
    private String imageUrl;
    private double pricePerItem;
    private int quantity;
    private double totalItemPrice;

    public CartItem(int productId, String productName, String imageUrl, double pricePerItem, int quantity, double totalItemPrice) {
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
        this.totalItemPrice = totalItemPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
