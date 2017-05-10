package com.thesis.brown.brown;


import android.content.Intent;
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
import android.widget.TableLayout;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        actionBar = getSupportActionBar();
        tabsAdapter = new MyAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new CategoryFragment());
        tabsAdapter.addFragment(new FeaturedFragment());
        tabsAdapter.addFragment(new AllFragment());

        viewPager.setAdapter(tabsAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_menu:
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.main_content, new MenuActivity())
//                        .commit();
                startActivity(new Intent(this, MenuActivity.class));
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        static List<Fragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
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
