package com.ckt.cc.gallery.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cc.gallery.R;
import com.ckt.cc.gallery.model.GalleryItem;

import java.util.List;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GalleryItem> mGalleryItems;

    private QueuePhotosGet mQueuePhotosGet;

    public GalleryAdapter(Context context, List<GalleryItem> galleryItems) {

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mGalleryItems = galleryItems;
    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.list_item_recyclerview, parent, false);

        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryHolder holder, int position) {
        holder.bindDrawable(mContext.getResources().getDrawable(R.mipmap.loading_img));

        mQueuePhotosGet.onQueuePhotosGet(holder,mGalleryItems.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return mGalleryItems.size();
    }


    public interface QueuePhotosGet {
        void onQueuePhotosGet(GalleryHolder holder,String url);
        void onQueuePhotosGet(GalleryHolder holder,String url,int destWidth, int destHeight);
    }

    public void setQueuePhotosGet(QueuePhotosGet queuePhotosGet) {
        mQueuePhotosGet = queuePhotosGet;
    }

}
