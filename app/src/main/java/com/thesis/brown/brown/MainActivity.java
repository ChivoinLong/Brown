package com.thesis.brown.brown;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.thesis.brown.brown.authentication.AccountSignInActivity;
import com.thesis.brown.brown.fragment.MenuFragment;
import com.thesis.brown.brown.fragment.MyCartFragment;
import com.thesis.brown.brown.store_list.MainListStore;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        LinearLayout headerLayout = (LinearLayout) mNavigationView.getHeaderView(0);
        headerLayout.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.content_main, new MenuFragment()).commit();

//        Intent intent = new Intent(this, AccountVerifyActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AccountSignInActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentManager manager = getSupportFragmentManager();

        switch (item.getItemId()){
            case R.id.nav_menu:
                setTitle("Menu");
                manager.beginTransaction().replace(R.id.content_main, new MenuFragment()).commit();
                break;

            case R.id.nav_shop_location:
                setTitle("Shop Location");
                manager.beginTransaction().replace(R.id.content_main, new MainListStore()).commit();
                break;
            case R.id.nav_my_cart:
                setTitle("My Cart");
                manager.beginTransaction().replace(R.id.content_main, new MyCartFragment()).commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
