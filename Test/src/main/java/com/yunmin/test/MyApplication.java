package com.yunmin.test;

import android.app.Application;
import android.util.Log;

/**
 * Created by luoyu on 2016/6/27.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(this, android.os.Process.myPid());
        Log.e("lym","application start,process name:"+processName);
    }
}
