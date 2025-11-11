package com.myfirstandroidjava.salesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.myfirstandroidjava.salesapp.R;
import com.myfirstandroidjava.salesapp.models.CartItem;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<CartItem> orderItems;

    public OrderAdapter(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        CartItem item = orderItems.get(position);

        holder.txtProductName.setText(item.getProductName());
        holder.txtPrice.setText(String.format("$%.2f", item.getPricePerItem()));
        holder.txtQuantity.setText("Qty: " + item.getQuantity());
        holder.txtTotal.setText(String.format("Total: $%.2f", item.getTotalItemPrice()));

        Glide.with(holder.itemView.getContext())
                .load("http://172.20.10.3:7002" + item.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_error)
                .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return orderItems != null ? orderItems.size() : 0;
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName, txtPrice, txtQuantity, txtTotal;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.cartImage);
            txtProductName = itemView.findViewById(R.id.cartProductName);
            txtPrice = itemView.findViewById(R.id.cartPrice);
            txtQuantity = itemView.findViewById(R.id.cartQuantity);
            txtTotal = itemView.findViewById(R.id.cartTotal);
        }
    }
}
