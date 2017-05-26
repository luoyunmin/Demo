package com.yunmin.touchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by luoyunmin on 2016/9/8.
 */
public class TouchView extends View {

    private static final String TAG = "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("lym", TAG + "-----dispatchTouchEvent");
        boolean b = super.dispatchTouchEvent(event);
        Log.e("lym", "dispatch b:" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("lym", TAG + "-----onTouchEvent");
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e("lym", "action_down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("lym", "action_move");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("lym", "action_up");
//                break;
//        }
//        return false;
        boolean b = super.onTouchEvent(event);
        Log.e("lym", "b:" + b);
        return b;
    }
}
