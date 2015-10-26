package com.otter.otter.common;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.otter.otter.utils.Utils;
import com.otter.otter.utils.SharePreferencesUtils;

import android.app.Application;

public class IApplication extends Application {
    private static IApplication mInstance;
    public static String host = "http://123.57.88.97/";

    public static IApplication getInstance() {
        if (mInstance == null) {
            mInstance = new IApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        Fresco.initialize(this);
        super.onCreate();
        mInstance = this;
        SharePreferencesUtils.init(this);
        //全局修改字体样式
        Utils.setApplicationTypeFace(this, "fonts/PingFangRegular.ttf");
    }

}
