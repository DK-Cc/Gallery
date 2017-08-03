package com.ckt.cc.gallery.model;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Cc on 2017/8/3.
 */

public class PreferencesUtils {

    public static final String ITEM_NUMBER = "item_number";

    public static int getItemNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(ITEM_NUMBER, 0);
    }

    public static void setItemNumber(Context context, int itemNumber) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(ITEM_NUMBER,
                itemNumber).apply();
    }

}
