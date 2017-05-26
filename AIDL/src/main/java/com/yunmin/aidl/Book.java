package com.yunmin.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luoyunmin on 2016/8/16.
 */
public class Book implements Parcelable {
    //:;
    private int id;
    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
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
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return "{id:" + id + ",name:" + name + "}";
    }
}
