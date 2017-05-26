package com.yunmin.touchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;

/**
 * Created by luoyunmin on 2016/9/8.
 */
public class TouchViewGroup extends LinearLayout {
    private static final String TAG = "TouchViewGroup";
    VelocityTracker mVelocityTracker;

    public TouchViewGroup(Context context) {
        super(context);
        init();
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("lym", TAG + "-----onInterceptTouchEvent");
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("lym", TAG + "-----dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("lym", TAG + "-----onTouchEvent");
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                Log.e("lym", "xVelocity:" + xVelocity);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
