package com.ciandt.worldwonders.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.dialog.DialogWebView;
import com.ciandt.worldwonders.helpers.ImageHelper;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by nlopes on 8/27/15.
 */
public class DetailFragment extends Fragment{

    private Wonder wonder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_wonder_detail);
        wonder = (Wonder)getArguments().getSerializable("wonder");

        if (wonder != null) {

            TextView textView = (TextView)view.findViewById(R.id.text_view);
            textView.setText(wonder.getDescription());

            CollapsingToolbarLayout ctb = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
            ctb.setTitle(wonder.getName());

            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            ImageHelper.setImage(imageView, wonder.getPhoto().split("\\.")[0], getContext());

            TextView txLink = (TextView) view.findViewById(R.id.link);
            txLink.setText(wonder.getUrl());
            txLink.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle(1);
                    bundle.putSerializable("wonder", wonder);
                    DialogWebView.show(getFragmentManager()).setArguments(bundle);
                }
            });

        }


    }
}
