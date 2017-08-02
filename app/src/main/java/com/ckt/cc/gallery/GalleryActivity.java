package com.ckt.cc.gallery;

import android.support.v4.app.Fragment;

public class GalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return GalleryFragment.newInstance();
    }
}
