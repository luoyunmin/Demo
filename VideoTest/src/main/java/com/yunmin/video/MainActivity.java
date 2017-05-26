package com.yunmin.video;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    int position;
    int height;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //手机屏幕宽高
        WindowManager windowManager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

//        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
//        mediaPlayer = new MediaPlayer();
//        surfaceHolder = surfaceView.getHolder();
//        //设置播放时打开屏幕
//        surfaceHolder.setKeepScreenOn(true);
        Uri uri = Uri.parse("");
        MediaMetadataRetriever rev = new MediaMetadataRetriever();

        rev.setDataSource(this, uri); //这里第一个参数需要Context，传this指针

        Bitmap bitmap = rev.getFrameAtTime(1000,

                MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        ((ImageView) findViewById(R.id.video_controller)).setImageBitmap(bitmap);

    }

    private void initVideo() throws IOException {
        Uri uri = Uri.parse("");
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置一个需要解析的视频
        mediaPlayer.setDataSource(MainActivity.this, uri);
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.prepareAsync();

    }

    private void setScreen() {
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(width, mediaPlayer.getVideoHeight() * width / mediaPlayer.getVideoWidth()));
    }

    private class SurfaceListener implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (position > 0) {
                try {
                    initVideo();
                    mediaPlayer.seekTo(position);
                    position = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        //释放资源
        mediaPlayer.release();
        super.onDestroy();
    }
}
