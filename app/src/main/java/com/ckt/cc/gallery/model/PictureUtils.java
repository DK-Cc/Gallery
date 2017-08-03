package com.ckt.cc.gallery.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Cc on 2017/7/25.
 */

public class PictureUtils {
    public static Bitmap getScaleBitmap(String path, float destWidth, float destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {

            float heightScale = 0;
            float widthScale = 0;

            if (destHeight == 0 && destWidth == 0) {
                return null;
            } else if (destHeight == 0) {
                widthScale = srcWidth / destWidth;
            } else if (destWidth == 0) {
                heightScale = srcHeight / destHeight;

            } else {
                heightScale = srcHeight / destHeight;
                widthScale = srcWidth / destWidth;
            }

            inSampleSize = Math.round(heightScale > widthScale ? heightScale : widthScale);
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 根据屏幕的大小进行缩放
     * @param path
     * @param activity
     * @return
     */
    public static Bitmap getScaledBitmap(String path, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaleBitmap(path, size.x, size.y);
    }


}
