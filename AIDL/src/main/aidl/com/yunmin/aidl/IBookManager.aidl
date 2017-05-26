package com.yunmin.aidl;

import com.yunmin.aidl.Book;
import com.yunmin.aidl.IOnNewBookArrivedListener;
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}