// IBookManager.aidl
package com.yunmin.test;
import com.yunmin.test.Book;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
