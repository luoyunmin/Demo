package com.yunmin.viewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by luoyunmin on 2016/9/6.
 */
public class ScrollerTestView extends View {
    private Scroller mScroll = null;

    public ScrollerTestView(Context context) {
        super(context);
        mScroll = new Scroller(context);
    }

    public ScrollerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroll = new Scroller(context);
    }

    //缓慢滚动到指定位置
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        Log.e("lym", "scrollX:" + scrollX + "-----destX:" + destX + "-----delta:" + delta);
        mScroll.startScroll(scrollX, 0, delta, 0, 5000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroll.computeScrollOffset()) {
            Log.e("lym", "true-----"+mScroll.getCurrX()+"-----"+mScroll.getCurrY());
            scrollTo(mScroll.getCurrX(), mScroll.getCurrY());
            postInvalidate();
        }
    }
}
