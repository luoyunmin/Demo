package com.yunmin.svg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by luoyunmin on 2017/3/6.
 */

public class FourthActivity extends AppCompatActivity implements View.OnClickListener {
    //;:
    private LinearLayout mShowView;
    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasureHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        mShowView = (LinearLayout) findViewById(R.id.show_view);
        mHiddenView = (LinearLayout) findViewById(R.id.hidden_view);
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasureHeight = (int) (mDensity * 40 + 0.5);
        mShowView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_view:
                if (mHiddenView.getVisibility() == View.GONE) {
                    animateOpen(mHiddenView);
                } else {
                    animateClose(mHiddenView);
                }
                break;
        }
    }

    private void animateOpen(View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHiddenViewMeasureHeight);
        animator.start();
    }

    private void animateClose(final View view) {
        int nowHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, nowHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

}
