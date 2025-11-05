package com.myfirstandroidjava.salesapp.network;

import com.myfirstandroidjava.salesapp.models.AddToCartRequest;
import com.myfirstandroidjava.salesapp.models.AddToCartResponse;
import com.myfirstandroidjava.salesapp.models.CartListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartAPIService {
    @POST("Cart/add-to-cart")
    Call<AddToCartResponse> addToCart(@Body AddToCartRequest addToCartRequest);

    @GET("Cart")
    Call<CartListResponse> getCart();
}
