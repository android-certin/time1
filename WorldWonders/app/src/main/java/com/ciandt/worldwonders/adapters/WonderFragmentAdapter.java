package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.fragments.WonderFragment;
import com.ciandt.worldwonders.model.Wonder;

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

        Wonder wonder = new Wonder();
        wonder.setId(position);

        WonderFragment wonderFragment = WonderFragment.newInstance(wonder);

        return wonderFragment;
    }

    @Override
    public int getCount() {
        return this.pageCount;
    }
}
