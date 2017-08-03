package com.ckt.cc.gallery.model;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.ckt.cc.gallery.recyclerview.GalleryAdapter;

import java.util.List;

/**
 * Created by Cc on 2017/8/2.
 */

public class DownloadMesAsyncTask extends AsyncTask<Void, Void, List<GalleryItem>> {

    private Context mContext;

    private RecyclerView mGalleryRecyclerView;

    private List<GalleryItem> mGalleryItems;

    public DownloadMesAsyncTask(Context context, RecyclerView galleryRecyclerView,
                                List<GalleryItem> galleryItems) {
        mContext = context;
        mGalleryRecyclerView = galleryRecyclerView;
        mGalleryItems = galleryItems;
    }

    @Override
    protected List<GalleryItem> doInBackground(Void... params) {
        return JsonParseUtils.parseMesJson(HttpUtils.getJsonContent(GalleryItem.URL));
    }

    @Override
    protected void onPostExecute(List<GalleryItem> galleryItems) {
        super.onPostExecute(galleryItems);

        mGalleryItems = galleryItems;

        mGalleryRecyclerView.setAdapter(new GalleryAdapter(mContext, mGalleryItems));

    }
}
