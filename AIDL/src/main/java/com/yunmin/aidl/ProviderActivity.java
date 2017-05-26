package com.yunmin.aidl;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by luoyunmin on 2016/8/21.
 */
public class ProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.yunmin.aidl.book.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
    }
}
