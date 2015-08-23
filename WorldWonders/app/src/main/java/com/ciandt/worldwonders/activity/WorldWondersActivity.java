package com.ciandt.worldwonders.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.WonderFragmentAdapter;

public class WorldWondersActivity extends AppCompatActivity {
    final int PAGE_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_wonders);

        ViewPager viewPager = (ViewPager)findViewById(R.id.pager_wonder);

        WonderFragmentAdapter wonderFragmentAdapter = new WonderFragmentAdapter(getSupportFragmentManager(), PAGE_COUNT);
        viewPager.setAdapter(wonderFragmentAdapter);
        viewPager.setCurrentItem(0);
    }
}
