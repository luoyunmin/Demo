package com.yunmin.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        persistToFile();
    }

    //;:
    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(1, "Hello World", false);
                File dir = new File(Constants.MY_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File cacheFile = new File(Constants.FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cacheFile));
                    objectOutputStream.writeObject(user);
                    Log.e("lym", "persist user:" + user);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (objectOutputStream != null)
                        try {
                            objectOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }).start();
    }
}
