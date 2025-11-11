package com.myfirstandroidjava.salesapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfirstandroidjava.salesapp.models.PayPalCreateRequest;
import com.myfirstandroidjava.salesapp.models.PayPalResponse;
import com.myfirstandroidjava.salesapp.network.PayPalAPIService;
import com.myfirstandroidjava.salesapp.network.RetrofitClient;
import com.myfirstandroidjava.salesapp.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private PayPalAPIService payPalAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        TokenManager tokenManager = new TokenManager(this);
        String token = tokenManager.getToken();
        payPalAPIService = RetrofitClient.getClient(this, token).create(PayPalAPIService.class);

        Button payBtn = findViewById(R.id.btnPay);
        payBtn.setOnClickListener(v -> startPayPalPayment(9.99));
    }

    private void startPayPalPayment(double amount) {
        payPalAPIService.createOrder(new PayPalCreateRequest(amount)).enqueue(new Callback<PayPalResponse>() {
            @Override
            public void onResponse(Call<PayPalResponse> call, Response<PayPalResponse> response) {
                if (response.isSuccessful()) {
                    String approvalUrl = response.body().approvalUrl;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(approvalUrl));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(PaymentActivity.this, "Failed to create order", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PayPalResponse> call, Throwable t) {
                Log.e("PayPal", "Error: " + t.getMessage());
            }
        });
    }
}