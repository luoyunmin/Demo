package com.yunmin.test2;

import android.os.Parcel;
import android.os.Parcelable;

import com.yunmin.aidl.Book;

import java.io.Serializable;

/**
 * Created by luoyu on 2016/7/14.
 */
public class User implements Serializable, Parcelable {
    // : ;
    public int userId;
    public String userName;
    public boolean isMale;

    public Book book;

    public User() {
    }

    public User(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected User(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.isMale = in.readInt() == 1;
        this.book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(userName);
        parcel.writeInt(isMale ? 1 : 0);
        parcel.writeParcelable(book, 0);
    }
}
