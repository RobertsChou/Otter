package com.otter.otterlibs.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;

import com.otter.otterlibs.BuildConfig;
import com.otter.otterlibs.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {

    public Utils() {
    }

    /**
     * 全局修改字体  发法在Application中调用
     * <p/>
     * MONOSPACE 需要到Style里面进行相应的配置 <item name="android:typeface">monospace</item>
     *
     * @param context
     */
    public static void setApplicationTypeFace(Context context, String font) {
        Typeface typeFacePingRegular = Typeface.createFromAsset(context.getAssets(), font);
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeFacePingRegular);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给TextView定制字体
     *
     * @param context
     * @param textView
     * @param font
     */
    public static void setSpecificTypeFace(Context context, TextView textView, String font) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), font);
        textView.setTypeface(face);
    }

    /**
     * 跳转到下一个Activity
     *
     * @param context
     * @param clzz
     */
    public static void goNext(Context context, Class clzz) {
        Intent intent = new Intent(context, clzz);
        context.startActivity(intent);
    }

    /**
     * Activity间跳转带动画
     *
     * @param context
     * @param clzz
     */
    public static void goNextWithAnimation(Context context, Class clzz) {
        Intent i = new Intent(context, clzz);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_freeze);
    }

    /**
     * 打印log日志
     *
     * @param tag
     * @param message
     */
    public static void log(String tag, String message) {
        Log.e(tag, message);
    }

    public static void log(String message) {
        if (BuildConfig.DEBUG) {
            Log.e("Otter", message);
        }
    }

    /**
     * 弹出Toast提示
     *
     * @param context
     * @param toast
     */
    public static void toast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * 简单 格式化时间戳
     *
     * @param t
     *
     * @return
     */
    public static String formatTime(long t) {
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatTime = dateformat1.format(new Date(t));
        return formatTime;
    }

    /**
     * Pretty 格式化时间戳   x之前
     *
     * @param t
     *
     * @return
     */
    public static String formatPrettyTime(long t) {
        PrettyTime prettyTime = new PrettyTime();
        String prettytime = prettyTime.format(new Date(t));
        return prettytime;
    }

    /***
     * 判断当前网络是否连接
     *
     * @param
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo == null) {
            return false;
        }
        if (netinfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前网络类型
     *
     * @param context
     *
     * @return
     */
    public static boolean netWorkType(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext()//获取应用上下文
                .getSystemService(Context.CONNECTIVITY_SERVICE);//获取系统的连接服务
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();//获取网络的连接情况
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
            //判断WIFI网
        } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            //判断3G网
            return false;
        }
        return false;
    }

    /**
     * 开启wifi网络
     *
     * @param context
     *
     * @return
     */
    public static void openWifi(Context context, boolean enabled) {
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(enabled);
    }

    /**
     * 判断链接是否为url
     *
     * @param url
     *
     * @return
     */
    public static boolean isUrl(String url) {
        boolean isUrl = false;
        if (URLUtil.isHttpsUrl(url) || URLUtil.isHttpUrl(url)) {
            isUrl = true;
        }
        return isUrl;
    }

    /***
     * 获取系统SDK Version
     */
    public static void setConfigChanges(Context context) {
        String systemType = getSystemType();
        int systemTypeInt = Integer.parseInt(systemType);
        if (systemTypeInt > 13) {

        }
    }

    /**
     * 获取设备版本号
     *
     * @return
     */
    public static String getDeviceSystemVersion() {
        return System.getProperty("os.version") + Build.VERSION.INCREMENTAL;
    }

    public static String getSystemType() {
        return Build.VERSION.SDK;//android.os.Build.VERSION.SDK //Build.MODEL + Build.PRODUCT
    }

    /**
     * 给输入汉字的text加粗
     *
     * @param textView
     */
    public static void blod(TextView textView) {
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * WebView 设置html
     *
     * @param html
     *
     * @return
     */
    public String setWebView(String html) {
        if (html == null) {
            return "";
        }
        String str = html.replaceAll("BACKGROUND\\s*:\\s*rgb\\([^\\)]*\\)", "BACKGROUND : rgb(242,241,239)");
        if (!TextUtils.isEmpty(str)) {
            html = str;
        }
        html =
                "<!DOCTYPE HTML><html><head><meta http-equiv=\"ConteOnt-Type\" content=\"text/html; "
                        + "charset=utf-8\"><meta content=\"width=device-width; initial-scale=1; minimum-scale=1.0; "
                        + "maximum-scale=2.0\" name=\"viewport\" /></head><body color='#414141' bgcolor='#F2F1EF'>"
                        + html + "</body></html>";
        Utils.log("TEST", html);
        return html;
    }

    /**
     * 获取当前应用版本号
     *
     * @param context
     *
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "没有获取到当前版本号";
        }
    }
}
