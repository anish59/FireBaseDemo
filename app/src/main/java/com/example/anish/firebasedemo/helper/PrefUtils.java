package com.example.anish.firebasedemo.helper;

import android.content.Context;

public class PrefUtils {

    private static final String REG_ID = "REG_ID";

    public static void setRegId(Context context, String lastDateimt) {
        Prefs.with(context).save(REG_ID, lastDateimt);
    }

    public static String getRegId(Context context) {
        return Prefs.with(context).getString(REG_ID, "");
    }

}
