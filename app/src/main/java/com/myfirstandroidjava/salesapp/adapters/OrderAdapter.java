package com.myfirstandroidjava.salesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myfirstandroidjava.salesapp.R;
import com.myfirstandroidjava.salesapp.models.CartItem;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<CartItem> orderItems;

    public OrderAdapter(ArrayList<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        CartItem item = orderItems.get(position);
        holder.productName.setText(item.getProductName());
        holder.quantity.setText("Qty: " + item.getQuantity());
        holder.price.setText(String.format("$%.2f", item.getPricePerItem()));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity, price;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cartProductName);
            quantity = itemView.findViewById(R.id.cartQuantity);
            price = itemView.findViewById(R.id.cartPrice);
        }
    }
}
