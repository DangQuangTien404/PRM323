package com.myfirstandroidjava.salesapp.models;

public class AddToCartRequest {
    private int productId;
    private int quantity;

    public AddToCartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
