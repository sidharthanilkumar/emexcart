package com.staging.emexcart.ViewPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.staging.emexcart.ViewPagerUi.OnsaleProducts;
import com.staging.emexcart.ViewPagerUi.FeaturedProducts;
import com.staging.emexcart.ViewPagerUi.TopRatedProducts;

public class ViewPagerAdapterOther extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAdapterOther(FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FeaturedProducts tab1 = new FeaturedProducts();
                return tab1;
            case 1:
                OnsaleProducts tab2 = new OnsaleProducts();
                return tab2;
            case 2:
                TopRatedProducts tab3 = new TopRatedProducts();
                return tab3;
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
