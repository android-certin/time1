package com.ciandt.worldwonders.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.WonderFragmentAdapter;

public class WonderFragment extends Fragment {
    final int PAGE_COUNT = 3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_world_wonders, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_wonder);

        WonderFragmentAdapter wonderFragmentAdapter = new WonderFragmentAdapter(getActivity().getSupportFragmentManager(), PAGE_COUNT);
        viewPager.setAdapter(wonderFragmentAdapter);
        viewPager.setCurrentItem(0);

        return view;
    }
}
