package com.yunmin.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by luoyunmin on 2016/8/16.
 */
public class BookManagerService extends Service {
    //:;
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    //    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<IOnNewBookArrivedListener>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListenerList.contains(listener))
//                Log.e("lym", "already exits.");
//            else
//                mListenerList.add(listener);
//            //:;
//            Log.e("lym", "rehisterLietener ,size:" + mListenerList.size());
            mListenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!mListenerList.contains(listener)) {
//                Log.e("lym", "not found , cannot unregister.");
//            } else {
//                mListenerList.remove(listener);
//                Log.e("lym", "unregister listener succeed.");
//            }//:;
//            Log.e("lym", "unrehisterLietener ,current size:" + mListenerList.size());
            mListenerList.unregister(listener);
        }
    };

    private void OnNewBookArrived(Book book) throws RemoteException {
//        mBookList.add(book);
//        Log.e("lym", "OnNewBookArrived, notify listeners: " + mListenerList.size());
//        for (int i = 0; i < mListenerList.size(); i++) {
//            IOnNewBookArrivedListener listener = mListenerList.get(i);
//            Log.e("lym", "OnNewBookArrived, notify listener:" + listener);
//            listener.onNewBookArrived(book);
//        }

        mBookList.add(book);
        int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (listener != null) {
                try {
                    listener.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "ANDROID"));
        mBookList.add(new Book(2, "IOS"));
        new Thread(new ServiceWorker()).start();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new Book#" + bookId);
                try {
                    OnNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
