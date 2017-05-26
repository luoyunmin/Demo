package com.yunmin.aidl;

import com.yunmin.aidl.Book;

interface IOnNewBookArrivedListener{
    void onNewBookArrived(in Book book);
}