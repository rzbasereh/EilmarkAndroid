package com.example.eilmarkandroid.ui.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eilmarkandroid.DBHelper;
import com.example.eilmarkandroid.MainActivity;
import com.example.eilmarkandroid.model.Product;
import com.example.eilmarkandroid.R;
import com.example.eilmarkandroid.ui.cart.adapter.CartItemsAdapter;
import com.example.eilmarkandroid.ui.cart.model.CartItem;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView cartRV;
    TextView totalPriceText;
    ConstraintLayout startCheckOut;

    ArrayList<Product> products;
    ArrayList<CartItem> items;
    DBHelper db;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_cart, null);
        initToolbar(view);
        db = new DBHelper(getActivity());
        cartRV = view.findViewById(R.id.cart_rv);
        totalPriceText = view.findViewById(R.id.total_price);
        startCheckOut = view.findViewById(R.id.start_check_out);

        startCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckOutActivity.class);
                startActivity(intent);
            }
        });

        products = Product.createProductsList(10);
        try {
            items = db.getCartItems();
            totalPriceText.setText(CartItem.getTotalPrice(products, items));
            configCartList(products, items);
        } catch (Exception e) {
            Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void configCartList(ArrayList<Product> products, ArrayList<CartItem> items) {
        CartItemsAdapter.RecyclerViewClickListener listener = (view, item, amount) -> {
            if (amount == 0) {
                db.deleteCartItem(item.getId());
                ((MainActivity) getContext()).showCartBudget();
                Toast.makeText(getContext(), "محصول از سبد شما حذف شد", Toast.LENGTH_SHORT).show();
            } else {
                db.updateCartItem(item.getId(), amount);
            }
            int totalPrice = Integer.parseInt((String) totalPriceText.getText(), 10);
            totalPrice += amount;
            totalPriceText.setText(String.valueOf(totalPrice));
        };

        CartItemsAdapter adapter = new CartItemsAdapter(products, items, getActivity(), listener);
        cartRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cartRV.setAdapter(adapter);
    }

    private void initToolbar(View view) {
        TextView toolbarTitleText;
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbarTitleText = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitleText.setText("سبد خرید");
    }
}
