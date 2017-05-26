package com.yunmin.animator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        //布局动画
//        RelativeLayout content= (RelativeLayout) findViewById(R.id.content);
//        ScaleAnimation sa=new ScaleAnimation(0,1,0,1);
//        sa.setDuration(5000);
//        LayoutAnimationController lac=new LayoutAnimationController(sa,0.5f);
//        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        content.setLayoutAnimation(lac);

        View tv =  findViewById(R.id.tv);
//        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300f);
//        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
//        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
//        ObjectAnimator.ofPropertyValuesHolder(tv,pvh1,pvh2,pvh3).setDuration(5000).start();
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
//        valueAnimator.setTarget(tv);
//        valueAnimator.setDuration(5000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                Float value = (Float) valueAnimator.getAnimatedValue();
//                Log.e("lym", value + "");
//            }
//        });
//        valueAnimator.start();

        //自定义动画
        tv.setAnimation(new CustomAnimator());

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
}
