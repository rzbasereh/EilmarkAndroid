package com.example.eilmarkandroid.ui.explore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.eilmarkandroid.R;

public class ExploreFragment extends Fragment {
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view =  inflater.inflate(R.layout.fragment_explore, null);
        initToolbar(view);
        return view;
    }

    private void initToolbar(View view) {
        TextView toolbarTitleText;
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbarTitleText = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitleText.setText("جستجو");
    }
}
