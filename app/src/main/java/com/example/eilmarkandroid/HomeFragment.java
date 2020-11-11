package com.example.eilmarkandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<Product> products;
    RecyclerView rvContacts;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanRceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        rvContacts = root.findViewById(R.id.rvContacts);
        products = Product.createProductsList(20);
        configProductsList(products);
        return root;
    }

    private void configProductsList(ArrayList<Product> products) {
        ProductsAdapter adapter = new ProductsAdapter(products);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvContacts.setAdapter(adapter);
    }
}
