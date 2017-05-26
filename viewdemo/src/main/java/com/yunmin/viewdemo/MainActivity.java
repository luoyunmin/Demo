package com.yunmin.viewdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    View helloWorld;
    ScrollerTestView scrollerView;
    TextView textView;
    CustomView customView;
    TextView custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //:;
        helloWorld = (View) findViewById(R.id.hello_world);
        helloWorld.post(new Runnable() {
            @Override
            public void run() {
                int left = helloWorld.getLeft();
                int right = helloWorld.getRight();
                int top = helloWorld.getTop();
                int bottom = helloWorld.getBottom();
                float translateX = helloWorld.getTranslationX();
                float translateY = helloWorld.getTranslationY();
                float x = helloWorld.getX();
                float y = helloWorld.getY();
                Log.e("lym", "translateX:" + translateX + "-----translateY:" + translateY);
                Log.e("lym", "x:" + x + "-----y:" + y);
                int height = helloWorld.getMeasuredHeight();
                int width = helloWorld.getMeasuredWidth();
                Log.e("lym", "left:" + left + "-----right:" + right + "-----top:" + top + "-----bottom:" + bottom + "-----width:" + width + "-----height:" + height);
            }
        });
        helloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("lym", "onclick");
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator.ofFloat(helloWorld, "translationX", 0, 500).setDuration(5000).start();
                    }
                });
            }
        }.start();

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        final int startX = 0;
//                        final int deltaX = 500;ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(5000);
//                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                            @Override
//                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                                float fraction = valueAnimator.getAnimatedFraction();
//                                textView.scrollTo(startX + (int) (deltaX * fraction), 0);
//                            }
//                        });
//                    }
//                });
//            }
//        }.start();
//        textView.scrollTo(500, 500);

        scrollerView = (ScrollerTestView) findViewById(R.id.scroller);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scrollerView.smoothScrollTo(5000, 5000);
                    }
                });
            }
        }.start();


        customView = (CustomView) findViewById(R.id.customView);
        custom = (TextView) findViewById(R.id.custom);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("lym", "custom");
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customView.smoothScrollTo(-1000, 0);
                    }
                });
            }
        }.start();
//        customView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e("lym", "onclick");
//            }
//        });
//        final int startX = 0;
//        final int deltaX = 1000;
//        final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(10000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                float fraction = valueAnimator.getAnimatedFraction();
//                customView.scrollTo(-(startX + (int) (deltaX * fraction)), 0);
//            }
//        });
//        animator.start();
        TestButton testbutton=(TestButton)findViewById(R.id.testbutton);
        testbutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        textView.scrollTo(scrollX, 0);
                        handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
            }
        }
    };
    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.textView:
//                final int startX = 0;
//                final int deltaX = -1000;
//                ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(5000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        float fraction = valueAnimator.getAnimatedFraction();
//                        textView.scrollTo(startX + (int) (fraction * deltaX), 0);
//                    }
//                });
//                animator.start();
//                LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) textView.getLayoutParams();
//                params.width += 3;
//                params.leftMargin += 100;
//                textView.requestLayout();
//                textView.setLayoutParams(params);
//                :;
                handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                break;
        }
    }
}
