package com.ciandt.worldwonders.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ciandt.worldwonders.fragments.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by ffranca on 8/21/15.
 */
public class WonderFragmentAdapter extends FragmentPagerAdapter {

    private List<Wonder> wonderList;

    public WonderFragmentAdapter(FragmentManager fm, List<Wonder> wonderList) {

        super(fm);
        this.wonderList = wonderList;
    }

    @Override
    public Fragment getItem(int position) {

        if (wonderList != null) {

            Wonder wonder = wonderList.get(position);

            HighlightFragment wonderFragment = HighlightFragment.newInstance(wonder);

            return wonderFragment;

        } else {
            return null;
        }

    }

    @Override
    public int getCount() {

        if (wonderList == null) {
            return 0;
        } else {
            return this.wonderList.size();
        }

    }
}
