package com.ciandt.worldwonders.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

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

        ImageView imageView =  (ImageView)view.findViewById(R.id.image_view);
        TextView textView = (TextView) view.findViewById(R.id.text_view);

        Bundle arguments = getArguments();

        if (arguments != null) {

            Wonder wonder = (Wonder) arguments.getSerializable(WONDER_EXTRA);

            setImage(imageView, wonder.getPhoto().split("\\.")[0]);

            textView.setText(wonder.getName());

        }
    }

    private void setImage(ImageView imageView, String pictureFilename) {

        int pictureResource = Helpers.getRawResourceID(getContext(), pictureFilename);

        Picasso.with(getContext())
                .load(pictureResource)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);
    }
}