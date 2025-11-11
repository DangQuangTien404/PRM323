package com.myfirstandroidjava.salesapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.myfirstandroidjava.salesapp.adapters.OrderAdapter;
import com.myfirstandroidjava.salesapp.models.CartItem;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView orderRecyclerView;
    private TextView tvOrderTotal;
    private Button btnPayment;
    private ArrayList<CartItem> orderItems;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        tvOrderTotal = findViewById(R.id.tvOrderTotal);
        btnPayment = findViewById(R.id.btnPayment);

        orderItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("cartItems");
        total = getIntent().getDoubleExtra("totalPrice", 0.0);

        tvOrderTotal.setText(String.format("Total: $%.2f", total));

        // Setup RecyclerView
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter adapter = new OrderAdapter(orderItems);
        orderRecyclerView.setAdapter(adapter);

        btnPayment.setOnClickListener(v -> {
            // Later, this will trigger the payment process.
            Toast.makeText(OrderActivity.this, "Payment functionality to be implemented.", Toast.LENGTH_SHORT).show();
        });
    }
}
