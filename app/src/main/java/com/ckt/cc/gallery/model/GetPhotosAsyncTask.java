package com.ckt.cc.gallery.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cc on 2017/8/3.
 */

public class GetPhotosAsyncTask extends AsyncTask<File,Void,List<Bitmap>> {

    private float mDestWidth;

    public GetPhotosAsyncTask(float destWidth) {
        mDestWidth = destWidth;
    }

    @Override
    protected List<Bitmap> doInBackground(File... params) {

        List<Bitmap> bitmaps = new ArrayList<>();

        for (File file : params) {
            if (file == null || !file.exists()) {

            } else {
//                Bitmap bitmap = PictureUtils.getScaleBitmap();
            }

        }


        return null;
    }

    @Override
    protected void onPostExecute(List<Bitmap> bitmaps) {
        super.onPostExecute(bitmaps);
    }
}
