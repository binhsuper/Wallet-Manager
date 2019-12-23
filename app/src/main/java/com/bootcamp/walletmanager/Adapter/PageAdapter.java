package com.bootcamp.walletmanager.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bootcamp.walletmanager.Fragments.chart1;
import com.bootcamp.walletmanager.Fragments.chart2;
import com.bootcamp.walletmanager.Fragments.chart3;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOftabs;

    public PageAdapter(FragmentManager fm, int numberOftabs) {
        super(fm);
        this.numberOftabs = numberOftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new chart1();
            case 1:
                return new chart2();
            case 2:
                return new chart3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

}
