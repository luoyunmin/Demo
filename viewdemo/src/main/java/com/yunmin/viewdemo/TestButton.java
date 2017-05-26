package com.yunmin.viewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by luoyunmin on 2016/9/7.
 */
public class TestButton extends TextView {

    private int mScaledTouchSlop;
    private int lastX=0;
    private int lastY=0;

    public TestButton(Context context) {
        this(context,null);
    }

    public TestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.e("lym", "sts:" + mScaledTouchSlop);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                Log.e("lym", "move,deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int) ViewHelper.getTranslationX(this) + deltaX;
                int translationY = (int) ViewHelper.getTranslationY(this) + deltaY;
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY=y;
        return true;
    }
}
