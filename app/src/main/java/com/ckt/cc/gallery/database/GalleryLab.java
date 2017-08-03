package com.ckt.cc.gallery.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.ckt.cc.gallery.database.GalleryDbSchema.GalleryTable;
import com.ckt.cc.gallery.model.GalleryItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Cc on 2017/8/3.
 */

public class GalleryLab {

    private Context mContext;
    private static SQLiteDatabase sDatabase;

    public GalleryLab(Context context) {
        mContext = context;
        sDatabase = new GalleryBaseHelper(mContext).getWritableDatabase();
    }

    public void addGallery(GalleryItem galleryItem) {
        ContentValues values = getContentValues(galleryItem);

        sDatabase.insert(GalleryTable.NAME, null, values);
    }


    public List<GalleryItem> getGalleryItems() {
        List<GalleryItem> galleryItems = new ArrayList<>();

        GalleryCursorWrapper cursor = queryGallery(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                galleryItems.add(cursor.getGalleryItem());
                cursor.moveToNext();
            }
        } finally {
        }
        cursor.close();

        return galleryItems;
    }

    public GalleryItem getGalleryItem(String id) {
        GalleryCursorWrapper cursor = queryGallery(GalleryTable.Cols.ID + " =?", new String[]{id});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getGalleryItem();
        } finally {
            cursor.close();
        }

    }

    /**
     * 把数据存储在目录："/storage/emulated/0/Android/data/包名/" 下
     * @param galleryItem
     * @return
     */
    public File getPhotoFile(GalleryItem galleryItem) {
        File filesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(filesDir, galleryItem.getPhotoFileName());
    }

    public void updateGallery(GalleryItem galleryItem) {
        String id = galleryItem.getId();
        ContentValues values = getContentValues(galleryItem);

        sDatabase.update(GalleryTable.NAME, values, GalleryTable.Cols.ID + " =?", new
                String[]{id});
    }

    private GalleryCursorWrapper queryGallery(String whereClause, String[] whereArgs) {
        Cursor cursor = sDatabase.query(GalleryTable.NAME, null, whereClause, whereArgs, null,
                null, null);

        return new GalleryCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(GalleryItem galleryItem) {
        ContentValues values = new ContentValues();
        values.put(GalleryTable.Cols.ID, galleryItem.getId());
        values.put(GalleryTable.Cols.COMPANY, galleryItem.getCompany());
        values.put(GalleryTable.Cols.SUMMARY, galleryItem.getSummary());
        values.put(GalleryTable.Cols.LOGO, galleryItem.getLogo());

        return values;
    }

}
