package com.ciandt.worldwonders.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.adapters.WonderFragmentAdapter;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.WondersRepository;

import java.util.List;

public class WonderFragment extends Fragment {

    final int QUANTITY_ITEMS = 3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_world_wonders, container, false);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_wonder);

        WondersRepository wondersRepository = new WondersRepository(getContext());
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "Wodners", "Carregando...");

        wondersRepository.getRandon(QUANTITY_ITEMS, new WondersRepository.WonderRandomListener() {

            @Override
            public void onWonderRandom(Exception e, List<Wonder> wonderList) {

                WonderFragmentAdapter wonderFragmentAdapter = new WonderFragmentAdapter(getActivity().getSupportFragmentManager(), wonderList);
                viewPager.setAdapter(wonderFragmentAdapter);
                progressDialog.dismiss();

            }
        });

        return view;
    }
}
