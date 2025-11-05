package com.myfirstandroidjava.salesapp.models;

import java.util.List;

public class CartListResponse {
    private int cartId;
    private double totalCartPrice;
    private List<CartItem> items;

    public CartListResponse(int cartId, double totalCartPrice, List<CartItem> items) {
        this.cartId = cartId;
        this.totalCartPrice = totalCartPrice;
        this.items = items;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public double getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
