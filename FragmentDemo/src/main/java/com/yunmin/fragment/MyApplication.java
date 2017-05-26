package com.yunmin.fragment;

import android.app.Application;

/**
 * Created by luoyu on 2016/3/29.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
    }
}
