package com.yunmin.test2;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by luoyu on 2016/7/14.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //;:
    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = null;
                File cacheFile = new File(Constants.FILE_PATH);
                if (cacheFile.exists()) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cacheFile));
                        user = (User) objectInputStream.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        if (objectInputStream!=null)
                            try {
                                objectInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }
        }).start();
    }
}
