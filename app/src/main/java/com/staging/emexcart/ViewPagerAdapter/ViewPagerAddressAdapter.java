package com.staging.emexcart.ViewPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.staging.emexcart.Fragments.BillingAddress;
import com.staging.emexcart.Fragments.ShippingAddress;
import com.staging.emexcart.ViewPagerUi.FeaturedProducts;
import com.staging.emexcart.ViewPagerUi.OnsaleProducts;
import com.staging.emexcart.ViewPagerUi.TopRatedProducts;

public class ViewPagerAddressAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAddressAdapter(FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                BillingAddress tab1 = new BillingAddress();
                return tab1;
            case 1:
                ShippingAddress tab2 = new ShippingAddress();
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
