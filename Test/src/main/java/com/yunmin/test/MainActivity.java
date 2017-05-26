package com.yunmin.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

//    private List<com.yunmin.aidl.Book> mBookList;

    BookManagerImpl mBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.addCategory("com.yunmin.test.haha");
//        : ;
                intent.setDataAndType(Uri.parse("content://abc"), "text/plain");
                startActivity(intent);
            }
        });
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mBookManager == null) {
                return;
            }

        }
    };
}
