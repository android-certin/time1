package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.fragments.WonderFragment;

/**
 * Created by ffranca on 8/21/15.
 */
public class WonderFragmentAdapter extends FragmentPagerAdapter {
    int pageCount;

    public WonderFragmentAdapter(FragmentManager fm, int pageCount) {

        super(fm);
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        WonderFragment wonderFragment = WonderFragment.newInstance(Integer.toString(position));

        return wonderFragment;
    }

    @Override
    public int getCount() {
        return this.pageCount;
    }
}
