package com.ckt.cc.gallery.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Cc on 2017/8/3.
 */

public class PhotosGetHandlerThread<T> extends HandlerThread {

    private static final String TAG = "PhotosGetHandlerThread";
    private static final int MESSAGE_DOWNLOAD = 0;
    private static final int MESSAGE_FIND_FILE = 1;

    private Handler mRequestHandler;
    private Handler mResponseHandler;
    private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>();
    private PhotosGetListener mPhotosGetListener;

    private boolean mHasQuit;
    private Bitmap mBitmap;

    public interface PhotosGetListener<T> {
        void onPhotosGet(T target, Bitmap photo);
    }

    public void setPhotosGetListener(PhotosGetListener<T> listener) {
        mPhotosGetListener = listener;
    }

    public PhotosGetHandlerThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleRequest(msg);
            }
        };

    }

    private void handleRequest(final Message msg) {

        final T target = (T) msg.obj;
        final String url = mRequestMap.get(target);

        if (url == null) {
            return;
        }

        if (msg.what == MESSAGE_DOWNLOAD) {
            byte[] bitmapBytes = HttpUtils.getByteContent(url);
            mBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
        } else if(msg.what == MESSAGE_FIND_FILE){
            mBitmap = PictureUtils.getScaleBitmap(url, msg.arg1, msg.arg2);
        }

        mResponseHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mRequestMap.get(target) != url || mHasQuit) {
                    return;
                }

                mRequestMap.remove(target);
                mPhotosGetListener.onPhotosGet(target, mBitmap);

            }
        });

    }

    public void queuePhotosGet(T targe, String url) {
        if (url == null) {
            mRequestMap.remove(targe);
        } else {
            mRequestMap.put(targe, url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, targe).sendToTarget();
        }
    }

    public void queuePhotosGet(T targe, String url, int destWidth, int destHeight) {
        if (url == null) {
            mRequestMap.remove(targe);
        } else {
            mRequestMap.put(targe, url);
            mRequestHandler.obtainMessage(MESSAGE_FIND_FILE, destWidth, destHeight, targe)
                    .sendToTarget();
        }
    }


    @Override
    public boolean quit() {

        mHasQuit = true;
        return super.quit();

    }

    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOAD);
        mRequestMap.clear();
    }


}
