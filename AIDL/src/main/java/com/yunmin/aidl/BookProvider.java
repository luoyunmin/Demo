package com.yunmin.aidl;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by luoyunmin on 2016/8/21.
 */
public class BookProvider extends ContentProvider {

    public static final String AUTHORITY = "com.yunmin.aidl.book.privider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    //;:
    @Override
    public boolean onCreate() {
        Log.e("lym", "onCreate , current thread:" + Thread.currentThread().getName());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Log.e("lym", "query,current thread:" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.e("lym", "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.e("lym", "insert");
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        Log.e("lym", "delect");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        Log.e("lym", "update");
        return 0;
    }
}
