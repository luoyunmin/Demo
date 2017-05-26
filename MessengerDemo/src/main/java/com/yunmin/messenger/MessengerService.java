package com.yunmin.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by luoyu on 2016/8/15.
 */
public class MessengerService extends Service {
    //:;
    private static final String TAG = "MessengerService";

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constanse.MSG_FROM_CLIENT:
                    try {
                        Log.e("lym", msg.getData().getString("msg"));
                        Messenger client = msg.replyTo;
                        Message replyMessage = Message.obtain(null, Constanse.MSG_FROM_CLIENT);
                        Bundle data = new Bundle();
                        data.putString("reply", "这是服务端，收到请回复");
                        replyMessage.setData(data);
                        client.send(replyMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
            super.handleMessage(msg);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
