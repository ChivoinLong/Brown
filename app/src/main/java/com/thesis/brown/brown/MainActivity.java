package com.thesis.brown.brown;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.thesis.brown.brown.StoreList.MainListStore;
import com.thesis.brown.brown.fragment.MenuFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    LinearLayout linFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
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
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.content_main, new MenuFragment()).commit();

        setControls();
        setEvents();
        startUp();
    }

    void startUp(){

    }

    void setEvents(){

    }

    void setControls(){
//        linFirstRun = (LinearLayout)findViewById(R.id.linFirstRun);
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
//                linFirstRun.setVisibility(View.GONE);
                manager.beginTransaction().replace(R.id.content_main, new MainListStore()).commit();
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
