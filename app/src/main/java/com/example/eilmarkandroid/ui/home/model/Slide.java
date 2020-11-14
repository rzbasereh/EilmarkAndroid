package com.example.eilmarkandroid.ui.home.model;

import com.example.eilmarkandroid.R;

import java.util.ArrayList;

public class Slide {
    private final String slug;
    private int image;

    public Slide(int image, String slug) {
        this.slug = slug;
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public int getImage() {
        return image;
    }

    private static int itemId = 0;

    public static ArrayList<Slide> createSlidesList(int num) {
        ArrayList<Slide> slides = new ArrayList<Slide>();

        for (int i = 1; i <= num; i++) {
            Slide slide = new Slide(R.drawable.slide, String.valueOf(++itemId));
            slides.add(slide);
        }

        return slides;
    }
}
