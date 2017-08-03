package com.ckt.cc.gallery;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cc.gallery.database.GalleryLab;
import com.ckt.cc.gallery.model.DownloadMesAsyncTask;
import com.ckt.cc.gallery.model.GalleryItem;
import com.ckt.cc.gallery.model.PhotosGetHandlerThread;
import com.ckt.cc.gallery.model.PreferencesUtils;
import com.ckt.cc.gallery.recyclerview.GalleryAdapter;
import com.ckt.cc.gallery.recyclerview.GalleryHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryFragment extends Fragment {

    private RecyclerView mGalleryRecyclerView;

    private List<GalleryItem> mGalleryItems;
    private PhotosGetHandlerThread<GalleryHolder> mPhotosGetHandlerThread;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        initItems();

        Handler responseHandler = new Handler();
        mPhotosGetHandlerThread = new PhotosGetHandlerThread<>(responseHandler);
        mPhotosGetHandlerThread.setPhotosGetListener(new PhotosGetHandlerThread
                .PhotosGetListener<GalleryHolder>() {

            @Override
            public void onPhotosGet(GalleryHolder target, Bitmap photo) {
                Drawable drawable = new BitmapDrawable(getResources(), photo);
                target.bindDrawable(drawable);
            }
        });

        mPhotosGetHandlerThread.start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        mGalleryRecyclerView = (RecyclerView) view.findViewById(R.id.gallery_recycler_view);
        mGalleryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        setupAdapter();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_gallery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_img:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initItems() {
        int itemNumber = PreferencesUtils.getItemNumber(getActivity());

        if (itemNumber > 0) {
            GalleryLab galleryLab = new GalleryLab(getActivity());
            mGalleryItems = galleryLab.getGalleryItems();
        } else {
            mGalleryItems = new ArrayList<>();
        }
    }

    private void updateItems() {

        new DownloadMesAsyncTask(getActivity(), mGalleryRecyclerView, mGalleryItems).execute();
    }

    private void setupAdapter() {
        mGalleryRecyclerView.setAdapter(new GalleryAdapter(getActivity(), mGalleryItems));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
