package com.yunmin.simulation.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by luoyunmin on 2017/3/20.
 */

public class ScreenUtil {

    /**
     * 获取屏幕相关参数
     *
     * @param context
     * @return DisplayMetrics 屏幕宽高
     */
    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 获取屏幕density
     *
     * @param context
     * @return 屏幕density
     */
    public static float getDeviceDensity(Context context) {
        return getScreenSize(context).density;
    }
}
