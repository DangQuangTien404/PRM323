package com.myfirstandroidjava.salesapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import com.myfirstandroidjava.salesapp.adapters.OrderAdapter;
import com.myfirstandroidjava.salesapp.models.CartItem;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvTotal;
    private OrderAdapter orderAdapter;
    private ArrayList<CartItem> cartItems;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.orderRecyclerView);
        tvTotal = findViewById(R.id.tvTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("cart_items");
        totalPrice = getIntent().getDoubleExtra("total_price", 0.0);

        if (cartItems != null) {
            orderAdapter = new OrderAdapter(cartItems);
            recyclerView.setAdapter(orderAdapter);
            tvTotal.setText(String.format("Total: $%.2f", totalPrice));
        }
    }
}
