package com.myfirstandroidjava.salesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfirstandroidjava.salesapp.models.CaptureRequest;
import com.myfirstandroidjava.salesapp.models.CaptureResponse;
import com.myfirstandroidjava.salesapp.network.PayPalAPIService;
import com.myfirstandroidjava.salesapp.network.RetrofitClient;
import com.myfirstandroidjava.salesapp.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayPalRedirectActivity extends AppCompatActivity {
    private PayPalAPIService payPalAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TokenManager tokenManager = new TokenManager(this);
        String token = tokenManager.getToken();
        payPalAPIService = RetrofitClient.getClient(this, token).create(PayPalAPIService.class);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null && uri.getScheme().equals("myapp")) {
            if (uri.getHost().equals("paypal") && uri.getPath().equals("/success")) {
                String token = uri.getQueryParameter("token");
                captureOrder(token);
            } else {
                Toast.makeText(this, "Payment canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void captureOrder(String orderId) {
        payPalAPIService.captureOrder(new CaptureRequest(orderId)).enqueue(new Callback<CaptureResponse>() {
            @Override
            public void onResponse(Call<CaptureResponse> call, Response<CaptureResponse> response) {
                if (response.isSuccessful() && response.body().success) {
                    Toast.makeText(PayPalRedirectActivity.this, "Payment success!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PayPalRedirectActivity.this, "Capture failed", Toast.LENGTH_LONG).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<CaptureResponse> call, Throwable t) {
                Log.e("PayPal", "Capture failed: " + t.getMessage());
                finish();
            }
        });
    }
}
