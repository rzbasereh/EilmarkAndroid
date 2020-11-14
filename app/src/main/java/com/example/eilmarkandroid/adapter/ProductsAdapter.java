package com.example.eilmarkandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eilmarkandroid.R;
import com.example.eilmarkandroid.model.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    public interface RecyclerViewClickListener {
        void onClick(View view, Product product);
    }

    private final RecyclerViewClickListener listener;
    private Context context;
    private final List<Product> products;

    public ProductsAdapter(List<Product> products, Context context, RecyclerViewClickListener listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(contactView, listener, products);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        Product contact = products.get(position);

        TextView title = holder.titleTextView;
        title.setText(contact.getTitle());

        TextView subTitle = holder.subTitleTextView;
        subTitle.setText(contact.getSub_title());

        TextView price = holder.priceTextView;
        price.setText(contact.getPrice() + " تومان");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public TextView subTitleTextView;
        public TextView priceTextView;
        public ImageButton cartBtn;
        private final RecyclerViewClickListener listener;
        private final List<Product> products;

        public ViewHolder(View itemView, RecyclerViewClickListener listener, List<Product> products) {
            super(itemView);
            this.listener = listener;
            this.products = products;

            titleTextView = itemView.findViewById(R.id.product_title);
            subTitleTextView = itemView.findViewById(R.id.product_subTitle);
            priceTextView = itemView.findViewById(R.id.product_price);
            cartBtn = itemView.findViewById(R.id.add_to_cart);
            cartBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, products.get(getAdapterPosition()));
        }
    }
}
