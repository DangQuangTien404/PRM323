package com.myfirstandroidjava.salesapp.network;

import com.myfirstandroidjava.salesapp.models.ProductDetailResponse;
import com.myfirstandroidjava.salesapp.models.ProductListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductAPIService {
    @GET("products")
    Call<ProductListResponse> getProducts(
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );

    @GET("products/{id}")
    Call<ProductDetailResponse> getProductDetail(@Path("id") int id);
}
