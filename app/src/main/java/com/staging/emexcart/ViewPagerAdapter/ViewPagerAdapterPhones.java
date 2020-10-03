package com.staging.emexcart.ViewPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.staging.emexcart.ViewPagerUi.BestSeller;
import com.staging.emexcart.ViewPagerUi.FeaturedProducts;
import com.staging.emexcart.ViewPagerUi.OnsaleProducts;
import com.staging.emexcart.ViewPagerUi.TopRatedProducts;
import com.staging.emexcart.ViewPagerUi.Trending;

public class ViewPagerAdapterPhones extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAdapterPhones(FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                BestSeller tab1 = new BestSeller();
                return tab1;
            case 1:
                Trending tab2 = new Trending();
                return tab2;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
