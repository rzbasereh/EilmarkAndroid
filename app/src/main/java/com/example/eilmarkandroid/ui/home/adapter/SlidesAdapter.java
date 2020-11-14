package com.example.eilmarkandroid.ui.home.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.eilmarkandroid.R;
import com.example.eilmarkandroid.ui.home.model.Slide;

import java.util.List;

public class SlidesAdapter extends PagerAdapter {
    List<Slide> slides;
    LayoutInflater inflater;
    Context context;

    public SlidesAdapter(List<Slide> slides, Context context) {
        this.slides = slides;
        this.context = context;
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_slide, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(slides.get(position).getImage());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
