package com.ckt.cc.gallery.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ckt.cc.gallery.database.GalleryDbSchema.GalleryTable;

/**
 * Created by Cc on 2017/8/3.
 */

public class GalleryBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "galleryBase.db";

    public GalleryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + GalleryTable.NAME + "(" + " _id integer primary " +
                "" + "key" + " autoincrement, " + GalleryTable.Cols.ID + ", " + GalleryTable.Cols
                .COMPANY + ", " + GalleryTable.Cols.SUMMARY + ", " + GalleryTable.Cols.LOGO + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
