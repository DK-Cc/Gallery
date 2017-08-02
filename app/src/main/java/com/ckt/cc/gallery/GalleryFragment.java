package com.ckt.cc.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cc on 2017/8/2.
 */

public class GalleryFragment extends Fragment{

    private RecyclerView mGalleryRecyclerView;


    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        mGalleryRecyclerView = (RecyclerView) view.findViewById(R.id.gallery_recycler_view);
        mGalleryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));


        return view;
    }





}
