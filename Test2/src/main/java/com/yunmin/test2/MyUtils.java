package com.yunmin.test2;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by luoyu on 2016/7/18.
 */
public class MyUtils {

    public static void colse(Closeable colseable) {
        try {
            if (colseable != null)
                colseable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
