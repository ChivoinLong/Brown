package com.thesis.brown.brown;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.thesis.brown.brown.fragment.AllFragment;
import com.thesis.brown.brown.fragment.CategoryFragment;
import com.thesis.brown.brown.fragment.FeaturedFragment;

/**
 * Created by Obi-Voin Kenobi on 12-Jun-17.
 */

public class AccountSignUpActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

//        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Nested Inner Class
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        final String[] tabNames = {
                CategoryFragment.TAB_NAME,
                FeaturedFragment.TAB_NAME,
                AllFragment.TAB_NAME
        };

        public MyViewPagerAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
//                    return CategoryFragment.newInstance(getArguments());
                case 1:
//                    return FeaturedFragment.newInstance(getArguments());
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }
}
