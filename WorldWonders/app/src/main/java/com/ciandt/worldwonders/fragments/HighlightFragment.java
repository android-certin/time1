package com.ciandt.worldwonders.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.ImageHelper;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by ffranca on 8/21/15.
 */
public class HighlightFragment extends Fragment {
    private View view;
    private static final String WONDER_EXTRA = "wonder";

    public static final HighlightFragment newInstance(Wonder wonder) {

        HighlightFragment wonderFragment =  new HighlightFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(WONDER_EXTRA, wonder);
        wonderFragment.setArguments(bundle);
        return wonderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_highlight, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        TextView textView = (TextView) view.findViewById(R.id.text_view);

        Bundle arguments = getArguments();

        if (arguments != null) {

            Wonder wonder = (Wonder) arguments.getSerializable(WONDER_EXTRA);

            ImageHelper.setImage(imageView, wonder.getPhoto().split("\\.")[0], getContext());

            textView.setText(wonder.getName());

        }
    }


}