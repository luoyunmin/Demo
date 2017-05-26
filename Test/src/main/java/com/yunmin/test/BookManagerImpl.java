package com.yunmin.test;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by luoyunmin on 2017/2/18.
 */
public class BookManagerImpl extends Binder implements com.yunmin.test.IBookManager{
    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
