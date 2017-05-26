package com.yunmin.touchdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luoyunmin on 2016/9/10.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#00FFFF"));
        setContentView(view);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                View contentView = ((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);
                if (view.equals(contentView)) {
                    Log.e("lym", "true");
                } else {
                    Log.e("lym", "false");
                }
            }
        }, 5000);
    }
}
