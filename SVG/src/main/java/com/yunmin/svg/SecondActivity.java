package com.yunmin.svg;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by luoyunmin on 2017/3/3.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    //:;
    ImageView img, topView, rightView, bottomView, leftView;

    private boolean mFlag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);

        topView = (ImageView) findViewById(R.id.top);
        topView.setOnClickListener(this);
        rightView = (ImageView) findViewById(R.id.right);
        rightView.setOnClickListener(this);
        bottomView = (ImageView) findViewById(R.id.bottom);
        bottomView.setOnClickListener(this);
        leftView = (ImageView) findViewById(R.id.left);
        leftView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            default:
                Toast.makeText(this, ""+v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startAnim() {
        ObjectAnimator imgAnim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0.5f);
        ObjectAnimator topAnim = ObjectAnimator.ofFloat(topView, "translationY", -200f);
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(rightView, "translationX", 200f);
        ObjectAnimator bottomAnim = ObjectAnimator.ofFloat(bottomView, "translationY", 200f);
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(leftView, "translationX", -200f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(imgAnim, topAnim, rightAnim, bottomAnim, leftAnim);
        set.start();
        mFlag = false;
    }

    private void closeAnim() {
        ObjectAnimator imgAnim = ObjectAnimator.ofFloat(img, "alpha", 0.5f, 1f);
        ObjectAnimator topAnim = ObjectAnimator.ofFloat(topView, "translationY", 0f);
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(rightView, "translationX", 0f);
        ObjectAnimator bottomAnim = ObjectAnimator.ofFloat(bottomView, "translationY", 0f);
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(leftView, "translationX", 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(imgAnim, topAnim, rightAnim, bottomAnim, leftAnim);
        set.start();
        mFlag = true;
    }
}
