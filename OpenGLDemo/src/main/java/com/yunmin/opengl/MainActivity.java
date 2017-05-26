package com.yunmin.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    //;:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //在oncreate时创建一个GLSurfaceView,并且设置为contentView
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GLSurfaceView gl = new GLSurfaceView(this);
        gl.setRenderer(new MyOpenGLRenderer());
        setContentView(gl);
    }
}
