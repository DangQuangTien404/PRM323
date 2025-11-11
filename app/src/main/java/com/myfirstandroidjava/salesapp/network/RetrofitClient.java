package com.myfirstandroidjava.salesapp.network;

import android.content.Context;

import com.myfirstandroidjava.salesapp.utils.Constants;
import com.myfirstandroidjava.salesapp.utils.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context, String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (token != null && !token.isEmpty()) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + token)
                            .method(original.method(), original.body());
                    return chain.proceed(requestBuilder.build());
                }
            });
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL + "api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    public static DeviceTokenAPIService getDeviceTokenAPIService(Context context) {
        TokenManager tokenManager = new TokenManager(context);
        String token = tokenManager.getToken();
        return getClient(context, token).create(DeviceTokenAPIService.class);
    }

    public static ChatAPIService getChatAPIService(Context context) {
        TokenManager tokenManager = new TokenManager(context);
        String token = tokenManager.getToken();
        return getClient(context, token).create(ChatAPIService.class);
    }
}