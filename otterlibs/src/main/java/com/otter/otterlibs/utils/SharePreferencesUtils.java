package com.otter.otterlibs.utils;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferencesUtils implements  Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String sharedPreferencesInfo = "baidu.golf";

    private static SharedPreferences saveInfo;
    private static Editor saveEditor;
    private static SharePreferencesUtils sharedPreferencesUtil = new SharePreferencesUtils();

    private static Context myContext;

    public static void init(Context context) {
        myContext = context;
    }

    public static SharePreferencesUtils getInstance(Context context) {
        myContext = context;
        if (saveInfo == null && myContext != null) {
            saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo, Context.MODE_PRIVATE);
            saveEditor = saveInfo.edit();
        }
        return sharedPreferencesUtil;
    }

    public static SharePreferencesUtils getInstance() {
        if (saveInfo == null && myContext != null) {
            saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo, Context.MODE_PRIVATE);
            saveEditor = saveInfo.edit();
        }
        return sharedPreferencesUtil;
    }
}
