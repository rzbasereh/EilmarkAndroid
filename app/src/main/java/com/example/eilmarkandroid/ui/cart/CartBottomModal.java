package com.example.eilmarkandroid.ui.cart;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eilmarkandroid.DBHelper;
import com.example.eilmarkandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CartBottomModal extends BottomSheetDialogFragment {
    private int productID;
    ImageButton addBtn, removeBtn, closeBtn;
    Button submitBtn;
    TextView amountText, payTextView, titleText;
    DBHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_bottom_modal, container, false);
        db = new DBHelper(getActivity());
        Bundle args = getArguments();
        titleText = view.findViewById(R.id.product_title);
        assert args != null;
        titleText.setText(args.getString("title"));
        productID = args.getInt("id");
        amountText = view.findViewById(R.id.amount);
        addBtn = view.findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        removeBtn = view.findViewById(R.id.remove);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct();
            }
        });
        submitBtn = view.findViewById(R.id.submit_cart);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(amountText.getText().toString(), 10);
                try {
                    boolean status = db.insertCartItem(productID, quantity);
                    if (status) {
                        Toast.makeText(getContext(), "محصول به سبد شما اضافه شد!", Toast.LENGTH_SHORT).show();
                        BottomNavigationView navigation = view.findViewById(R.id.navigation);
                        navigation.getOrCreateBadge(R.id.navigation_cart).setNumber(1);
                    } else {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), e+"", Toast.LENGTH_LONG).show();
                }
            }
        });
        closeBtn = view.findViewById(R.id.close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    private void addProduct() {
        int amount = Integer.parseInt(amountText.getText().toString(), 10);
        amount += 1;
        amountText.setText(String.valueOf(amount));
    }

    private void removeProduct() {
        int amount = Integer.parseInt(amountText.getText().toString(), 10);
        amount += -1;
        if (amount == 0) {
            dismiss();
        }
        amountText.setText(String.valueOf(amount));
    }
}
