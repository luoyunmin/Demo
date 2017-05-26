package com.yunmin.test2;

import android.os.Environment;

/**
 * Created by luoyu on 2016/7/14.
 */
public class Constants {
    public static final String MY_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/yunmin/Test2/";

    public static final String FILE_PATH = MY_PATH + "usercache";
}
