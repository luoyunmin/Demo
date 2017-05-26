package com.yunmin.binderpool;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ISecurityCenter mSecurityCenter = null;
    ICompute mCompute = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //:;
        new Thread() {
            @Override
            public void run() {
                super.run();
                doWork();
            }
        }.start();
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getsInstance(this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        Log.e("lym", "visit ISecurityCenter");
        String msg = "helloworld-安卓";
        try {
            String password = mSecurityCenter.encrypt(msg);
            System.out.println("encrypt:" + password);
            System.out.println("decrypt:" + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.e("lym", "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);
        try {
            System.out.println("3+5="+mCompute.add(3,5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
