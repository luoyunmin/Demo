package com.yunmin.dagger2;

import android.app.Application;

/**
 * Created by luoyunmin on 2017/5/25.
 */

public class MyApplication extends Application {
    //:;
    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {

    }
}
