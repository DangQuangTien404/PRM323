package com.myfirstandroidjava.salesapp.network;

import android.content.Context;

import com.myfirstandroidjava.salesapp.utils.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // Make sure to use your computer's IP address, not localhost
    // 10.0.2.2 is the special alias for the Android emulator to access the host machine's localhost
    private static final String BASE_URL = "http://172.20.10.3:7002/api/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        TokenManager tokenManager = new TokenManager(context);
        String token = tokenManager.getToken();

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
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }
}