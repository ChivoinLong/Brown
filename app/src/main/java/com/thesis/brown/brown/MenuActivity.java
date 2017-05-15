package com.thesis.brown.brown;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.thesis.brown.brown.Category.CategoryFragment;
import com.thesis.brown.brown.StoreList.MainListStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Obi-Voin Kenobi on 26-Mar-17.
 */

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private MyAdapter tabsAdapter;
    private ActionBar actionBar;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    TabLayout tabLayout;
    FrameLayout frameContent;
    String currentTap = "MENU";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        actionBar = getSupportActionBar();
        tabsAdapter = new MyAdapter(getSupportFragmentManager(), 3);
        tabsAdapter.addFragment(new CategoryFragment());
        tabsAdapter.addFragment(new FeaturedFragment());
        tabsAdapter.addFragment(new AllFragment());

        viewPager.setAdapter(tabsAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        actionBar.setHomeButtonEnabled(false);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setTitle("Tab ActionBar using ViewPager");

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


        setControls();
        setEvents();
        startUp();
    }

    void setControls(){
        frameContent = (FrameLayout)findViewById(R.id.content_main);
    }

    void setEvents(){

    }

    void startUp(){

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawer(GravityCompat.START);

        android.app.FragmentManager manager = getFragmentManager();

        switch (item.getItemId()){
            case R.id.nav_menu:

                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);

                if (!currentTap.equals("MENU")){
                    manager.beginTransaction().remove(getFragmentManager().findFragmentById(R.id.content_main)).commit();
                }

                currentTap = "MENU";

                break;

            case R.id.nav_shop_location:

                tabLayout.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);

//                manager.beginTransaction().replace(R.id.content_main, new MainListStore()).commit();

                currentTap = "LOCATION";

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        static List<Fragment> fragments = new ArrayList<>();
        int numPages;

        public MyAdapter(FragmentManager fm, int numPages) {
            super(fm);

            this.numPages = numPages;
        }

        @Override
        public int getCount() {
            return numPages;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt(CategoryFragment.POSITION_KEY, position);
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = fragments.get(position).toString();
            return title.substring(0, title.lastIndexOf("{"));
        }

        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }
    }
}
