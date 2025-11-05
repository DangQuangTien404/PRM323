package com.myfirstandroidjava.salesapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myfirstandroidjava.salesapp.adapters.ProductAdapter;
import com.myfirstandroidjava.salesapp.fragments.AlertFragment;
import com.myfirstandroidjava.salesapp.fragments.CartFragment;
import com.myfirstandroidjava.salesapp.fragments.ChatFragment;
import com.myfirstandroidjava.salesapp.fragments.MapFragment;
import com.myfirstandroidjava.salesapp.fragments.ShopFragment;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        // Default: open Shop (Home) page
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ShopFragment())
                .commit();
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_shop) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ShopFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.nav_cart) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CartFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.nav_chat) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ChatFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.nav_alerts) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AlertFragment())
                    .commit();
            return true;
        } else if (itemId == R.id.nav_map) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MapFragment())
                    .commit();
            return true;
        }

        return false;
    }
}