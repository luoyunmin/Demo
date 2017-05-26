package com.yunmin.test;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by luoyu on 2016/6/27.
 */
public class MyUtils {

    public static String getProcessName(Context c, int pid) {
        ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo apps : runningApps) {
            if (apps.pid == pid) {
                return apps.processName;
            }
        }
        return null;
    }
}
