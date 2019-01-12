package com.lenscommerce.android.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.lenscommerce.android.R;
import com.lenscommerce.android.ui.widget.AdvanceDrawerLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.nav_drawer_home)
    AdvanceDrawerLayout drawerLayout;
    @BindView(R.id.navigation_home)
    NavigationView navigationView;
    @BindView(R.id.iv_menu_home)
    AppCompatImageView icMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupNavigation();
    }

    private void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
        icMenu.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_cat:
                drawerLayout.closeDrawer(GravityCompat.START);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            icMenu.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            icMenu.setImageResource(R.drawable.ic_menu_black_24dp);
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
