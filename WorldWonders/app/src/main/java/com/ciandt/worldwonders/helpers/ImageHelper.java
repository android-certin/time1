package com.ciandt.worldwonders.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.ciandt.worldwonders.R;
import com.squareup.picasso.Picasso;

/**
 * Created by nlopes on 8/26/15.
 */
public class ImageHelper {

    public static void setImage(ImageView imageView, String pictureFilename, Context context) {

        int pictureResource = Helpers.getRawResourceID(context, pictureFilename);

        Picasso.with(context)
                .load(pictureResource)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);
    }


}
