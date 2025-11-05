package com.myfirstandroidjava.salesapp.network;

import com.myfirstandroidjava.salesapp.models.CreateOrderRequest;
import com.myfirstandroidjava.salesapp.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderAPIService {
    @POST("/api/Order")
    Call<Order> createOrder(@Body CreateOrderRequest request);

    @GET("/api/Order/order-detail/{orderId}")
    Call<Order> getOrderDetail(@Path("orderId") int orderId);

    @PATCH("/api/Order/{orderId}/status")
    Call<Void> updateOrderStatus(@Path("orderId") int orderId);

    @GET("/api/Order")
    Call<List<Order>> getAllOrders();
}
