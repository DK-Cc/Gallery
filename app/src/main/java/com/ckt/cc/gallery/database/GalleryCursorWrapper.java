package com.ckt.cc.gallery.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ckt.cc.gallery.database.GalleryDbSchema.GalleryTable;
import com.ckt.cc.gallery.model.GalleryItem;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Cc on 2017/8/3.
 */

public class GalleryCursorWrapper extends CursorWrapper {

    public GalleryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public GalleryItem getGalleryItem() {
        GalleryItem galleryItem = new GalleryItem();
        galleryItem.setId(getString(getColumnIndex(GalleryTable.Cols.ID)));
        galleryItem.setCompany(getString(getColumnIndex(GalleryTable.Cols.COMPANY)));
        galleryItem.setSummary(getString(getColumnIndex(GalleryTable.Cols.SUMMARY)));
        galleryItem.setLogo(getString(getColumnIndex(GalleryTable.Cols.LOGO)));

        return galleryItem;
    }


}
