package com.yunmin.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by luoyunmin on 2017/4/5.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.print("onHandlerIntent");
        if(intent!=null){
            if(intent.getAction()=="com.yunmin.myintentservice"){
                init();
            }
        }
    }

    private void init() {
        Log.e("lym", "onHandlerIntent Thread:" + Thread.currentThread().getName());
    }
}
