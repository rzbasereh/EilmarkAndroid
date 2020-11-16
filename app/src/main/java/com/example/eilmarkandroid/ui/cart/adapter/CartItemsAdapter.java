package com.example.eilmarkandroid.ui.cart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eilmarkandroid.DBHelper;
import com.example.eilmarkandroid.MainActivity;
import com.example.eilmarkandroid.adapter.ProductsAdapter;
import com.example.eilmarkandroid.model.Product;
import com.example.eilmarkandroid.R;
import com.example.eilmarkandroid.ui.cart.model.CartItem;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    public interface RecyclerViewClickListener {
        void onClick(View view, CartItem item, int amount);
    }

    private final RecyclerViewClickListener listener;
    List<Product> products;
    List<CartItem> cartItems;
    Context context;

    public CartItemsAdapter(List<Product> products, List<CartItem> cartItems, Context context, RecyclerViewClickListener listener) {
        this.products = products;
        this.cartItems = cartItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart_product, parent, false);
        return new ViewHolder(view, cartItems, listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        Product product = products.stream().filter(instance -> (item.getId() == instance.getId())).findAny().orElse(null);
        TextView title = holder.titleTextView;
        TextView price = holder.priceTextView;
        TextView amount = holder.amountTextView;
        ImageButton removeItemBtn = holder.removeItemBtn;


        if (item.getQuantity() != 1) {
            removeItemBtn.setImageResource(R.drawable.ic_dash_primary_24dp);
        }
        amount.setText(String.valueOf(item.getQuantity()));
        if (product != null) {
            title.setText(product.getTitle());
            price.setText(String.valueOf(product.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView, priceTextView, amountTextView, totalPriceText;
        ImageButton addItemBtn, removeItemBtn;
        DBHelper db;
        CartItemsAdapter.RecyclerViewClickListener listener;
        List<CartItem> items;

        public ViewHolder(@NonNull View itemView, List<CartItem> items, CartItemsAdapter.RecyclerViewClickListener listener) {
            super(itemView);
            this.items = items;
            this.listener = listener;

            db = new DBHelper(itemView.getContext());
            titleTextView = itemView.findViewById(R.id.product_title);
            priceTextView = itemView.findViewById(R.id.product_price);
            amountTextView = itemView.findViewById(R.id.amount);
            addItemBtn = itemView.findViewById(R.id.add);
            removeItemBtn = itemView.findViewById(R.id.remove);

            addItemBtn.setOnClickListener(this);
            removeItemBtn.setOnClickListener(this);

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            int amount = Integer.parseInt((String) amountTextView.getText(), 10);
            CartItem item = items.get(getAdapterPosition());

            if (v == addItemBtn) {
                amount += 1;
                if (amount > 1) {
                    removeItemBtn.setImageResource(R.drawable.ic_dash_primary_24dp);
                }
                amountTextView.setText(String.valueOf(amount));
            } else {
                amount -= 1;
                if (amount == 1) {
                    removeItemBtn.setImageResource(R.drawable.ic_trash_primary_20dp);
                }
                amountTextView.setText(String.valueOf(amount));
            }
            listener.onClick(v, item, amount);
        }
    }

    private void deleteItem(int position) {
        cartItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartItems.size());
    }
}
