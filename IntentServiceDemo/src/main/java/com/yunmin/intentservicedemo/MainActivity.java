package com.yunmin.intentservicedemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //;:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
//                intent.setAction("com.yunmin.myintentservice");
//                startService(intent);
//            }
//        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.LEFT);
            slideTransition.setDuration(500);
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Pair<String, String> pair = new Pair<>("1", "2");
        Log.e("lym", "pair first:" + pair.first + "-----second:" + pair.second);
        Log.e("lym", pair.toString());
        View decorView = getWindow().getDecorView();
        View statusBar = decorView.findViewById(android.R.id.statusBarBackground);
        View navBar = decorView.findViewById(android.R.id.navigationBarBackground);
        if (statusBar != null) {
            Log.e("lym", "statusBar NonNull!");
            statusBar.setBackgroundColor(Color.parseColor("#00ffff"));
        }
        if (navBar != null) {
            Log.e("lym", "navBar NonNull!");
            navBar.setBackgroundColor(Color.parseColor("#FF00FF"));
        }
    }
}
