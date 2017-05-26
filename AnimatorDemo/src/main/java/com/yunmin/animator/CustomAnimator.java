package com.yunmin.animator;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by luoyunmin on 2017/2/20.
 */
public class CustomAnimator extends Animation {

    private Camera mCamera = new Camera();
    private int mCenterWidth;
    private int mCenterHeight;
    private float mRotateY = 10;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        //电视关闭效果
//        final Matrix matrix = t.getMatrix();
//        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);

        //3d旋转效果
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        //使用Camera设置旋转角度
        mCamera.rotateY(mRotateY * interpolatedTime);
        //将旋转作用到matrix上
        mCamera.getMatrix(matrix);
        mCamera.restore();
        //通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //电视关闭效果
        //设置默认时长
//        setDuration(1000);
//        //动画结束后保留状态
//        setFillAfter(true);
//        //设置默认的插值器
//        setInterpolator(new BounceInterpolator());
//
//        mCenterWidth = width / 2;
//        mCenterHeight = height / 2;

        //3d旋转效果
        //设置默认时长
        setDuration(10000);
        //动画结束后保留状态
        setFillAfter(true);
        //设置默认的插值器
        setInterpolator(new BounceInterpolator());

        mCenterHeight = height / 2;
        mCenterWidth = width / 2;

    }
}
