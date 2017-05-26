package com.yunmin.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luoyu on 2016/6/30.
 */
public class ParcelableDemo implements Parcelable {
    //:  ;

    public int userId;
    public String userName;
    public boolean isMale;

    public Book book;

    public ParcelableDemo(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected ParcelableDemo(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<ParcelableDemo> CREATOR = new Creator<ParcelableDemo>() {
        @Override
        public ParcelableDemo createFromParcel(Parcel in) {
            return new ParcelableDemo(in);
        }

        @Override
        public ParcelableDemo[] newArray(int size) {
            return new ParcelableDemo[size];
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
        parcel.writeParcelable(book,0);
    }
}
