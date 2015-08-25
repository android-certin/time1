package com.ciandt.worldwonders.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nlopes on 8/25/15.
 */

public class WonderItemAdpater extends RecyclerView.Adapter<WonderItemAdpater.ViewHolder> {

    private List<Wonder> wonderList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
       
        TextView textView;
        ImageView imageView;


        public ViewHolder(View v) {

            super(v);
            textView = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image_view);

        }
    }

    public WonderItemAdpater(Context context, List<Wonder> wonderList) {

        this.wonderList = wonderList;
        this.context = context;

    }

    @Override
    public WonderItemAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wonder, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Wonder wonder = wonderList.get(position);
        String pictureFilename = wonder.getPhoto().split("\\.")[0];

        holder.textView.setText(wonder.getName());

        int pictureResource = Helpers.getRawResourceID(context, pictureFilename);

        Picasso.with(context)
                .load(pictureResource)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return wonderList.size();
    }
}
