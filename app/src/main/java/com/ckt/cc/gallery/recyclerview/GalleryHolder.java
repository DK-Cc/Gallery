package com.ckt.cc.gallery.recyclerview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ckt.cc.gallery.R;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryHolder extends RecyclerView.ViewHolder {

    private ImageView mItemImageView;


    public GalleryHolder(View itemView) {
        super(itemView);

        mItemImageView = (ImageView) itemView.findViewById(R.id.iv_item);
    }

    public void bindDrawable(Drawable drawable) {
        mItemImageView.setBackground(drawable);
    }

}
