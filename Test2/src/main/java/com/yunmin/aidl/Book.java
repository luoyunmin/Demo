package com.yunmin.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luoyu on 2016/7/14.
 */
public class Book implements Parcelable {
    protected Book(Parcel in) {
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
