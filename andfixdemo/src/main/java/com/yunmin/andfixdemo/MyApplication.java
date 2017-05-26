package com.yunmin.andfixdemo;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by luoyunmin on 2017/3/30.
 */

public class MyApplication extends Application {

    //热修复文件的文件
    private String packageNamePath = "/com.yunmin.andfixdemo";
    private String patchFilePath = "/bug.apatch";

    PatchManager mPatchManager;
    String appVersion;

    @Override
    public void onCreate() {
        super.onCreate();
        mPatchManager = new PatchManager(this);
        try {
            appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mPatchManager.init(appVersion);
        mPatchManager.loadPatch();

        String packageNamePathFileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + packageNamePath;
        File packageNamePathFile = new File(packageNamePathFileStr);
        if (!packageNamePathFile.exists()) {
            packageNamePathFile.mkdir();
        }
        String patchFilePathStr = Environment.getExternalStorageDirectory().getAbsolutePath() + packageNamePath + patchFilePath;
        File patchFile = new File(patchFilePathStr);
        if (patchFile.exists()) {
            //patch文件存在
            try {
                mPatchManager.addPatch(patchFilePathStr);
            } catch (IOException e) {
                Log.e("lym", "热修复出错！");
                e.printStackTrace();
            }
        } else {
            //patch文件不存在
            Log.e("lym", "没有需要修复的BUG！");
        }

    }
}
