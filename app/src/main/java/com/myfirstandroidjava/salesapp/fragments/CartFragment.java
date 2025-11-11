package com.myfirstandroidjava.salesapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstandroidjava.salesapp.OrderActivity;
import com.myfirstandroidjava.salesapp.R;
import com.myfirstandroidjava.salesapp.adapters.CartAdapter;
import com.myfirstandroidjava.salesapp.models.CartListResponse;
import com.myfirstandroidjava.salesapp.network.CartAPIService;
import com.myfirstandroidjava.salesapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView tvTotal;
    private Button btnCheckout;
    private CartAPIService cartAPIService;
    private CartListResponse cartListResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        tvTotal = view.findViewById(R.id.tvTotal);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAPIService = RetrofitClient.getClient(requireContext()).create(CartAPIService.class);

        fetchCartData();

        btnCheckout.setOnClickListener(v -> {
            if (cartListResponse != null) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("cart_items", cartListResponse.getItems());
                intent.putExtra("total_price", cartListResponse.getTotalCartPrice());
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Cart data is not available.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchCartData() {
        Call<CartListResponse> call = cartAPIService.getCart();

        call.enqueue(new Callback<CartListResponse>() {
            @Override
            public void onResponse(Call<CartListResponse> call, Response<CartListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cartListResponse = response.body();
                    recyclerView.setAdapter(new CartAdapter(cartListResponse.getItems()));
                    tvTotal.setText(String.format("Total: $%.2f", cartListResponse.getTotalCartPrice()));
                } else {
                    Toast.makeText(getContext(), "Failed to load cart.", Toast.LENGTH_SHORT).show();
                    Log.e("CART_LIST_ERROR", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CartListResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
