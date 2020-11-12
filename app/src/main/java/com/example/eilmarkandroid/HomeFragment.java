package com.example.eilmarkandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<Product> products;
    RecyclerView rvContacts;
    Button showAllBtn;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanRceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        rvContacts = root.findViewById(R.id.rvContacts);
        showAllBtn = root.findViewById(R.id.show_all);
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBottomModal modal = new CartBottomModal();
                modal.show(getFragmentManager(), "modal");
            }
        });
        products = Product.createProductsList(20);
        configProductsList(products);
        return root;
    }

    private void configProductsList(ArrayList<Product> products) {
        ProductsAdapter adapter = new ProductsAdapter(products, getActivity());
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvContacts.setAdapter(adapter);
    }
}
