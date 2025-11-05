package com.myfirstandroidjava.salesapp.network;

import com.myfirstandroidjava.salesapp.models.CaptureRequest;
import com.myfirstandroidjava.salesapp.models.CaptureResponse;
import com.myfirstandroidjava.salesapp.models.PayPalCreateRequest;
import com.myfirstandroidjava.salesapp.models.PayPalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PayPalAPIService {
    @POST("paypal/create-order")
    Call<PayPalResponse> createOrder(@Body PayPalCreateRequest request);

    @POST("paypal/capture-order")
    Call<CaptureResponse> captureOrder(@Body CaptureRequest request);
}
