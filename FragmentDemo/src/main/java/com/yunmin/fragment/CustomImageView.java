package com.yunmin.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by luoyu on 2016/3/24.
 */
public class CustomImageView extends ImageView implements View.OnTouchListener{
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
