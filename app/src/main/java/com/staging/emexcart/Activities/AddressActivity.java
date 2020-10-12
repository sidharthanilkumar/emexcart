package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.staging.emexcart.R;
import com.staging.emexcart.ViewPagerAdapter.ViewPagerAdapterOther;
import com.staging.emexcart.ViewPagerAdapter.ViewPagerAddressAdapter;

public class AddressActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();
        setUpTopTabs();
    }

    private void initView(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.addressPager);
    }

    private void setUpTopTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Billing Address"));
        tabLayout.addTab(tabLayout.newTab().setText("Shipping Address"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAddressAdapter adapter = new ViewPagerAddressAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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