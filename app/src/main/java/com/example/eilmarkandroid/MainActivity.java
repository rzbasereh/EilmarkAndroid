package com.example.eilmarkandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.eilmarkandroid.ui.cart.CartFragment;
import com.example.eilmarkandroid.ui.explore.ExploreFragment;
import com.example.eilmarkandroid.ui.home.HomeFragment;
import com.example.eilmarkandroid.ui.profile.ProfileFragment;
import com.example.eilmarkandroid.ui.wish.WishFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        db.getWritableDatabase();
        navigation = findViewById(R.id.navigation);

        //loading the default fragment
        loadFragment(new HomeFragment());
        showCartBudget();

        //getting bottom navigation view and attaching the listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_explore:
                    fragment = new ExploreFragment();
                    break;

                case R.id.navigation_cart:
                    fragment = new CartFragment();
                    break;

                case R.id.navigation_wish:
                    fragment = new WishFragment();
                    break;

                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    break;
            }

            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void showCartBudget() {
        BadgeDrawable drawable = navigation.getOrCreateBadge(R.id.navigation_cart);
        drawable.setNumber(db.getCartItems().size());
        drawable.setVisible(true);
    }

}