package com.yunmin.download;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by luoyu on 2016/4/16.
 */
public class MyDownLoadFileDao {
    private static final String TAG = "DownloadFileDAO";
    private static MyDownLoadFileDao dao = null;
    private Context context;

    private MyDownLoadFileDao(Context context) {
        this.context = context;
    }

    synchronized public static MyDownLoadFileDao getInstance(Context context) {
        if (dao == null) {
            dao = new MyDownLoadFileDao(context);
        }
        return dao;
    }

    /**
     * ��ȡ���ݿ�����
     *
     * @return
     */
    public SQLiteDatabase getConnection() {
        SQLiteDatabase sqliteDatabase = null;
        try {
            sqliteDatabase = new DBHelper(context).getReadableDatabase();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return sqliteDatabase;
    }

    /**
     * ��������
     *
     * @param
     */
    public void insertDownloadFile(DownLoadBean downLoadBean) {
//        if(downLoadBean == null) {
//            return;
//        }
//        //��������Ѿ����ڣ�ֱ���޸�
//        if(getAppContentByUrl(downLoadBean.getNurl()) != null) {
//            updateDownloadFile(downLoadBean);
//            return;
//        }
//        SQLiteDatabase database = getConnection();
//        try {
//            String sql = "insert into download_file(app_name, url, download_percent, status) values (?,?,?,?)";
//            Object[] bindArgs = { downLoadBean.getTitle(), downLoadBean.getUrl(), downLoadBean.getDownloadPercent()
//                    , downLoadBean.getStatus().getValue()};
//            database.execSQL(sql, bindArgs);
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        } finally {
//            if (null != database) {
//                database.close();
//            }
//        }
    }

    /**
     * ����url��ȡ�����ļ���Ϣ
     *
     * @param url
     * @return
     */
    public AppContent getAppContentByUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        SQLiteDatabase database = getConnection();
        AppContent appContent = null;
        Cursor cursor = null;
        try {
            String sql = "select * from download_file where url=?";
            cursor = database.rawQuery(sql, new String[]{url});
            if (cursor.moveToNext()) {
                appContent = new AppContent(cursor.getString(1), cursor.getString(2));
                appContent.setDownloadPercent(cursor.getInt(3));
                appContent.setStatus(AppContent.Status.getByValue(cursor.getInt(4)));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (null != database) {
                database.close();
            }
            if (null != cursor) {
                cursor.close();
            }
        }
        return appContent;
    }

    /**
     * ����������Ϣ
     *
     * @param
     */
    public void updateDownloadFile(DownLoadBean downLoadBean) {
//        if (appContent == null) {
//            return;
//        }
//        SQLiteDatabase database = getConnection();
//        try {
//            Log.e(TAG, "update download_file,app name:" + appContent.getName() + ",url:" + appContent.getUrl()
//                    + ",percent" + appContent.getDownloadPercent() + ",status:" + appContent.getStatus().getValue());
//            String sql = "update download_file set app_name=?, url=?, download_percent=?, status=? where url=?";
//            Object[] bindArgs = {appContent.getName(), appContent.getUrl(), appContent.getDownloadPercent()
//                    , appContent.getStatus().getValue(), appContent.getUrl()};
//            database.execSQL(sql, bindArgs);
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        } finally {
//            if (null != database) {
//                database.close();
//            }
//        }
    }

    /**
     * ��ȡ���������ļ���¼
     *
     * @return
     */
    public List<DownLoadBean> getAll() {
//        SQLiteDatabase database = getConnection();
//        List<AppContent> list = new ArrayList<AppContent>();
//        Cursor cursor = null;
//        try {
//            String sql = "select * from download_file";
//            cursor = database.rawQuery(sql, null);
//            while (cursor.moveToNext()) {
//                AppContent appContent = new AppContent(cursor.getString(1), cursor.getString(2));
//                appContent.setDownloadPercent(cursor.getInt(3));
//                appContent.setStatus(AppContent.Status.getByValue(cursor.getInt(4)));
//                list.add(appContent);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        } finally {
//            if (null != database) {
//                database.close();
//            }
//            if (null != cursor) {
//                cursor.close();
//            }
//        }
//        return list;
        return null;
    }

    /**
     * ����urlɾ����¼
     *
     * @param url
     */
    public void delByUrl(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        SQLiteDatabase database = getConnection();
//        try {
//            String sql = "delete from download_file where url=?";
//            Object[] bindArgs = {url};
//            database.execSQL(sql, bindArgs);
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        } finally {
//            if (null != database) {
//                database.close();
//            }
//        }
    }
}
