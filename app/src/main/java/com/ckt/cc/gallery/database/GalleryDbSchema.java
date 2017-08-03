package com.ckt.cc.gallery.database;

/**
 * Created by Cc on 2017/8/3.
 */

public class GalleryDbSchema {

    public static final class GalleryTable {
        public static final String NAME = "gallery";

        public static final class Cols {
            public static final String ID = "id";
            public static final String COMPANY = "company";
            public static final String SUMMARY = "summary";
            public static final String LOGO = "logo";
        }
    }
}
