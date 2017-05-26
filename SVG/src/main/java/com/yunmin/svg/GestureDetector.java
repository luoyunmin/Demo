package com.yunmin.svg;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * Created by luoyunmin on 2017/3/7.
 */

public abstract class GestureDetector {
    private static final String TAG = "VersionedGestureDetector";

    OnGestureListener mListener;

    public static GestureDetector newInstance(Context context,
                                              OnGestureListener listener) {
        final int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
        GestureDetector detector = null;
        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            detector = new CupcakeDetector();
        } else if (sdkVersion < Build.VERSION_CODES.FROYO) {
            detector = new EclairDetector();
        } else {
            detector = new FroyoDetector(context);
        }

//        Log.d(TAG, "Created new " + detector.getClass());
        detector.mListener = listener;

        return detector;
    }

    public abstract boolean onTouchEvent(MotionEvent ev);

    public interface OnGestureListener {
        public void onDrag(float dx, float dy);

        public void onScale(float scaleFactor);
    }

    private static class CupcakeDetector extends GestureDetector {
        float mLastTouchX;
        float mLastTouchY;

        float getActiveX(MotionEvent ev) {
            return ev.getX();
        }

        float getActiveY(MotionEvent ev) {
            return ev.getY();
        }

        boolean shouldDrag() {
            return true;
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    mLastTouchX = getActiveX(ev);
                    mLastTouchY = getActiveY(ev);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    final float x = getActiveX(ev);
                    final float y = getActiveY(ev);

                    if (shouldDrag()) {
                        mListener.onDrag(x - mLastTouchX, y - mLastTouchY);
                    }

                    mLastTouchX = x;
                    mLastTouchY = y;
                    break;
                }
            }
            return true;
        }
    }

    private static class EclairDetector extends CupcakeDetector {
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = INVALID_POINTER_ID;
        private int mActivePointerIndex = 0;

        @Override
        float getActiveX(MotionEvent ev) {
            return ev.getX(mActivePointerIndex);
        }

        @Override
        float getActiveY(MotionEvent ev) {
            return ev.getY(mActivePointerIndex);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            final int action = ev.getAction();
            //No.1
            //开始写代码，请结合所有代码在这里实现，计算手指在屏幕上移动的位置。从而方便其他调用这个类的地方更新屏幕上小机器人的位置。小机器人跟随手指移动。
            switch (action & MotionEvent.ACTION_MASK) {
                //:;
                case MotionEvent.ACTION_DOWN:
                    mActivePointerId = ev.getPointerId(0);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mActivePointerId = INVALID_POINTER_ID;
                    break;
                case MotionEvent.ACTION_UP:
                    int pointerIndex = ev.findPointerIndex(mActivePointerId);
                    int pointerId = ev.getPointerId(pointerIndex);
                    if (pointerId == mActivePointerId) {
                        int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mActivePointerId=ev.getPointerId(newPointerIndex);
                    }
                    break;


            }

            mActivePointerIndex = ev.findPointerIndex(mActivePointerId != INVALID_POINTER_ID ? mActivePointerId : 0);
            //end_code
            return super.onTouchEvent(ev);
        }
    }

    private static class FroyoDetector extends EclairDetector {
        private ScaleGestureDetector mDetector;

        public FroyoDetector(Context context) {
            mDetector = new ScaleGestureDetector(context,
                    new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                        @Override
                        public boolean onScale(ScaleGestureDetector detector) {
                            mListener.onScale(detector.getScaleFactor());
                            return true;
                        }
                    });
        }

        @Override
        boolean shouldDrag() {
            return !mDetector.isInProgress();
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            mDetector.onTouchEvent(ev);
            return super.onTouchEvent(ev);
        }
    }
}
