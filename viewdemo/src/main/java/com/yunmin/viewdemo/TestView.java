package com.yunmin.viewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by luoyunmin on 2016/9/4.
 */
public class TestView extends View implements GestureDetector.OnGestureListener {
    GestureDetector mGestureDetector;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(this);
        mGestureDetector.setIsLongpressEnabled(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e("lym", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.e("lym", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.e("lym", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("lym", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.e("lym", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("lym", "onFling");
        return false;
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
}
