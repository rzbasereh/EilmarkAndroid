package com.example.eilmarkandroid;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CartBottomModal extends BottomSheetDialogFragment {
    ImageButton addBtn, removeBtn, closeBtn;
    Button submitBtn;
    TextView amountText, payTextView, titleText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_bottom_modal, container, false);
        Bundle args = getArguments();
        titleText = view.findViewById(R.id.product_title);
        titleText.setText(args.getString("title"));
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
