package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.fragments.WonderFragment;

/**
 * Created by ffranca on 8/21/15.
 */
public class WonderFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;

    public WonderFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        WonderFragment wonderFragment =  new WonderFragment();

        return wonderFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
