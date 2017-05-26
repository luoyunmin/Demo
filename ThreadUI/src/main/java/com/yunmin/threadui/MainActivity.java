package com.yunmin.threadui;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //:;
    private Button clickMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickMe = (Button) findViewById(R.id.click_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                TextView textView = new TextView(MainActivity.this);
                textView.setText("子线程");
                textView.setBackgroundColor(Color.parseColor("#00FFFF"));
                ViewManager viewManager = MainActivity.this.getWindowManager();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams(200, 200, 200, 200,
                        WindowManager.LayoutParams.FIRST_SUB_WINDOW,
                        WindowManager.LayoutParams.TYPE_TOAST, PixelFormat.OPAQUE);
                getWindowManager().addView(textView, params);
                Looper.loop();
            }
        };
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
