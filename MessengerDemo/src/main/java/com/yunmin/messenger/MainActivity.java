package com.yunmin.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //:;
    int i = 0;
    private Messenger mService;
    private IBinder mBinder;

    private Button mMessengerBtn;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = iBinder;
            Log.e("lym", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("lym", "onServiceDisconnected");
        }
    };

    private Messenger mReplyMessanger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constanse.MSG_FROM_CLIENT:
                    Bundle data = msg.getData();
                    String replyStr = data.getString("reply");
                    Log.e("lym", replyStr);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessengerBtn = (Button) findViewById(R.id.messenger);
        mMessengerBtn.setOnClickListener(this);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.messenger:
                try {
                    if (mBinder != null) {
                        mService = new Messenger(mBinder);
                        Message msg = Message.obtain(null, Constanse.MSG_FROM_CLIENT);
                        Bundle data = new Bundle();
                        data.putString("msg", "这是客户端的第" + i + "条信息,收到请回复");
                        msg.setData(data);
                        msg.replyTo = mReplyMessanger;
                        mService.send(msg);
                    } else {
                        Log.e("lym", "mBinder null");
                    }
                    i++;
                } catch (Exception e) {
                    Log.e("lym", "Exception");
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
