package com.example.eilmarkandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
        products = Product.createProductsList(10);
        configProductsList(products);
        return root;
    }

    private void configProductsList(ArrayList<Product> products) {
        ProductsAdapter.RecyclerViewClickListener listener = (view, product) -> {
            Bundle args = new Bundle();
            args.putString("title", product.getTitle());
            CartBottomModal modal = new CartBottomModal();
            modal.setArguments(args);
            modal.show(getFragmentManager(), "modal");
        };
        ProductsAdapter adapter = new ProductsAdapter(products, getActivity(), listener);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvContacts.setAdapter(adapter);
    }
}
