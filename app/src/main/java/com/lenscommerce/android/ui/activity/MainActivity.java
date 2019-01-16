package com.lenscommerce.android.ui.activity;

import android.content.Intent;
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
import androidx.appcompat.widget.AppCompatTextView;
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
    @BindView(R.id.tv_main_cart_size)
    AppCompatTextView tvCartSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupNavigation();
        setupCartIcon();
    }

    private void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
        icMenu.setOnClickListener(this);
    }

    private void setupCartIcon() {
        // TODO: 14/01/2019 Setup Cart Size
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
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_home:
                //do nothing
                break;
            case R.id.nav_cat:
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
                break;
            case R.id.nav_cart:
                //do nothing
                break;
            case R.id.nav_special_offer:
                //do nothing
                break;
            case R.id.nav_latest_products:
                //do nothing
                break;
            case R.id.nav_popular_products:
                //do nothing
                break;
            case R.id.nav_setting:
                //do nothing
                break;
            case R.id.nav_aboutUs:
                //do nothing
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
