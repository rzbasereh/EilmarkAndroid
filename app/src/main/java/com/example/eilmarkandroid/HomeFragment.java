package com.example.eilmarkandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ArrayList<Product> products;
    RecyclerView rvContacts;
    Button showAllBtn;
    DotsIndicator dotsIndicator;
    ViewPager viewPager;


    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanRceState) {
        View root = inflater.inflate(R.layout.fragment_home, null);
        rvContacts = root.findViewById(R.id.rvContacts);
        showAllBtn = root.findViewById(R.id.show_all);
        products = Product.createProductsList(10);
        configProductsList(products);
        initViewPager(root);
        return root;
    }

    private void initViewPager(View view) {
        dotsIndicator = view.findViewById(R.id.dots_indicator);
        viewPager = view.findViewById(R.id.view_pager);
        List<Slide> slides = Slide.createSlidesList(5);
        SlidesAdapter adapter = new SlidesAdapter(slides, getContext());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
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
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        rvContacts.setAdapter(adapter);
    }
}
