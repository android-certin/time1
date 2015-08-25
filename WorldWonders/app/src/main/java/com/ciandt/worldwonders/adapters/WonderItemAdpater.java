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

public class WonderItemAdpater extends RecyclerView.Adapter<WonderItemAdpater.ViewHolder> {

    private List<Wonder> wonderList;
    private Context context;
    private OnSelectItem onSelectItem;

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

        final Wonder wonder = wonderList.get(position);
        String pictureFilename = wonder.getPhoto().split("\\.")[0];

        final TextView textView = holder.textView;
        textView.setText(wonder.getName());

        setImage(holder, pictureFilename);

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(onSelectItem != null) {
                    onSelectItem.onSelectItem(wonder);
                }
            }
        });

    }

    private void setImage(ViewHolder holder, String pictureFilename) {

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

    public interface OnSelectItem {
        void onSelectItem(Wonder wonder);
    }

    public void setOnSelectItem(OnSelectItem onSelectItem) {
        this.onSelectItem = onSelectItem;
    }
}


