package com.tisen.note.utils;

import android.content.Context;

import cn.bmob.v3.BmobConfig;

/**
 * Created by tisen on 2016/10/24.
 */
public class BmobHelper {

    public final static String APPLICATION_ID = "7ce187fab34c97eb6cae1c7aad7580f4";

    public static BmobConfig getConfig(Context context) {
        BmobConfig config = new BmobConfig.Builder(context).
                setApplicationId(APPLICATION_ID).
                setConnectTimeout(4).build();
        return config;
    }
}
