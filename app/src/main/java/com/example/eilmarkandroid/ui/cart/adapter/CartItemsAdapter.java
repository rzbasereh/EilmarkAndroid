package com.example.eilmarkandroid.ui.cart.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eilmarkandroid.model.Product;
import com.example.eilmarkandroid.R;
import com.example.eilmarkandroid.ui.cart.model.CartItem;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {
    List<Product> products;
    List<CartItem> cartItems;
    Context context;

    public CartItemsAdapter(List<Product> products, List<CartItem> cartItems, Context context) {
        this.products = products;
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart_product, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        Product product = products.stream().filter(instance -> (item.getId() == instance.getId())).findAny().orElse(null);
        TextView title = holder.titleTextView;
        TextView price = holder.priceTextView;
        TextView amount = holder.amountTextView;
        if (product != null) {
            title.setText(product.getTitle());
            price.setText(String.valueOf(product.getPrice()));
            amount.setText(String.valueOf(item.getQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, priceTextView, amountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.product_title);
            priceTextView = itemView.findViewById(R.id.product_price);
            amountTextView = itemView.findViewById(R.id.amount);
        }
    }
}
