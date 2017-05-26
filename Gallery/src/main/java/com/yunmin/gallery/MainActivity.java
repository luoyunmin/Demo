package com.yunmin.gallery;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    GalleryFlow gallery;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader(getApplicationContext());

        list.add("http://pic1.win4000.com/pic/9/17/7ccc839737.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839738.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839739.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839740.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839741.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839742.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839743.jpg");
        list.add("http://pic1.win4000.com/pic/9/17/7ccc839744.jpg");
        gallery = (GalleryFlow) findViewById(R.id.gallery_flow);
        gallery.setOnItemClickListener(this);
        gallery.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //设置adapter需要在gallery绘制完成后，不然gallery的高度为0
                gallery.setAdapter(new GalleryAdapter(MainActivity.this, list));
                gallery.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initImageLoader(Context applicationContext) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(applicationContext);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
