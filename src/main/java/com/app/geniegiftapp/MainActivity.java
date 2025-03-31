package com.app.geniegiftapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            Log.d(TAG, "Nav item clicked: " + itemId);
            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
                Log.d(TAG, "Switching to HomeFragment");
            } else if (itemId == R.id.nav_browse) {
                selectedFragment = new BrowseFragment();
                Log.d(TAG, "Switching to BrowseFragment");
            } else if (itemId == R.id.nav_cart) {
                selectedFragment = new CartFragment();
                Log.d(TAG, "Switching to CartFragment");
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
                Log.d(TAG, "Switching to ProfileFragment");
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                Log.d(TAG, "Fragment transaction committed");
            } else {
                Log.e(TAG, "No fragment selected for ID: " + itemId);
            }
            return true;
        });

        Log.d(TAG, "Setting default HomeFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}