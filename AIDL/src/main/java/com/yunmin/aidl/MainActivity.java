package com.yunmin.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //;:

    private static final int MESSAGE_BEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BEW_BOOK_ARRIVED:
                    Log.e("lym", "receive new book :" + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(new MyServiceDeathHanler(),0);
                mRemoteBookManager = bookManager;
                List<Book> list = bookManager.getBookList();
                Log.e("lym", "query book list, list type:" + list.getClass().getCanonicalName());
                Log.e("lym", "query book list:" + list.toString());
                Book newBook = new Book(3, "Android 编程权威指南");
                bookManager.addBook(newBook);
                Log.e("lym", "add book:" + newBook);
                List<Book> newList = bookManager.getBookList();
                Log.e("lym", "query book list:" + newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBookManager = null;
            Log.e("lym", "onServiceDisconnected binder died.");
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_BEW_BOOK_ARRIVED, book).sendToTarget();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                mRemoteBookManager.unRegisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(mConnection);
            super.onDestroy();
        }
    }

    private class MyServiceDeathHanler implements IBinder.DeathRecipient{

        @Override
        public void binderDied() {

        }
    }
}
