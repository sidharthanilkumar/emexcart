package com.staging.emexcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.staging.emexcart.R;
import com.staging.emexcart.ViewPagerAdapter.ViewPagerAdapterOther;
import com.staging.emexcart.ViewPagerAdapter.ViewPagerAdapterPhones;

public class HomeFragment extends Fragment {
    TabLayout tabLayout,tabLayout2;
    ViewPager viewPager,viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        setUpTopTabs();
        setUpBottomTabs();
        return  view;
    }

    private void setUpBottomTabs() {
        tabLayout2.addTab(tabLayout2.newTab().setText("Trending"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Best Seller"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAdapterPhones adapter = new ViewPagerAdapterPhones(getActivity().getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPager2.setAdapter(adapter);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initView(View root){
        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.CategoryPager);
        tabLayout2 = root.findViewById(R.id.tabLayout2);
        viewPager2 = root.findViewById(R.id.CategoryPager2);
    }

    private void setUpTopTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Featured"));
        tabLayout.addTab(tabLayout.newTab().setText("On Sale"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Rated"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAdapterOther adapter = new ViewPagerAdapterOther(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}