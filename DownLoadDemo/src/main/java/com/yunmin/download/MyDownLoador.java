package com.yunmin.download;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by luoyu on 2016/4/16.
 */
public class MyDownLoador {
    public static final String TAG = "Downloador";
    private static final int THREAD_POOL_SIZE = 5;  //线程池大小为9
    private static final int THREAD_NUM = 1;  //每个文件3个线程下载
    private static final int GET_LENGTH_SUCCESS = 1;
    public static final Executor THREAD_POOL_EXECUTOR = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private List<MyDownLoadTask> tasks;
    private InnerHandler handler = new InnerHandler();

    private DownLoadBean downLoadBean; //待下载的应用
    private long downloadLength; //下载过程中记录已下载大小
    private long fileLength;
    private Context context;
    private String downloadPath;

    public MyDownLoador(Context context, DownLoadBean downLoadBean) {
        this.context = context;
        this.downLoadBean = downLoadBean;
        this.downloadPath = DownloadUtils.getDownloadPath();
    }

    /**
     * 开始下载
     */
    public void download() {
        if (TextUtils.isEmpty(downloadPath)) {
            Toast.makeText(context, "未找到SD卡", Toast.LENGTH_SHORT).show();
            return;
        }
        if (downLoadBean == null) {
            throw new IllegalArgumentException("download content can not be null");
        }
        new Thread() {
            @Override
            public void run() {
                //获取文件大小
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(downLoadBean.getNurl());
                HttpResponse response = null;
                try {
                    response = client.execute(request);
                    fileLength = response.getEntity().getContentLength();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                } finally {
                    if (request != null) {
                        request.abort();
                    }
                }
                //计算出该文件已经下载的总长度
                List<DownloadInfo> lists = DownloadInfoDAO.getInstance(context.getApplicationContext())
                        .getDownloadInfosByUrl(downLoadBean.getNurl());
                for (DownloadInfo info : lists) {
                    downloadLength += info.getDownloadLength();
                }

                //插入文件下载记录到数据库
                MyDownLoadFileDao.getInstance(context.getApplicationContext()).insertDownloadFile(downLoadBean);
                Message.obtain(handler, GET_LENGTH_SUCCESS).sendToTarget();
            }
        }.start();
    }

    /**
     * 开始创建AsyncTask下载
     */
    private void beginDownload() {
        Log.e(TAG, "beginDownload" + downLoadBean.getNurl());
        downLoadBean.setStatus(DownLoadBean.Status.WAITING);
        long blockLength = fileLength / THREAD_NUM;
        for (int i = 0; i < THREAD_NUM; i++) {
            long beginPosition = i * blockLength;//每条线程下载的开始位置
            long endPosition = (i + 1) * blockLength;//每条线程下载的结束位置
            if (i == (THREAD_NUM - 1)) {
                endPosition = fileLength;//如果整个文件的大小不为线程个数的整数倍，则最后一个线程的结束位置即为文件的总长度
            }
            MyDownLoadTask task = new MyDownLoadTask(i, beginPosition, endPosition, this, context);
            task.executeOnExecutor(THREAD_POOL_EXECUTOR, downLoadBean.getNurl());
            if (tasks == null) {
                tasks = new ArrayList<MyDownLoadTask>();
            }
            tasks.add(task);
        }
    }

    /**
     * 暂停下载
     */
    public void pause() {
        for (MyDownLoadTask task : tasks) {
            if (task != null && (task.getStatus() == AsyncTask.Status.RUNNING || !task.isCancelled())) {
                task.cancel(true);
            }
        }
        tasks.clear();
        downLoadBean.setStatus(DownLoadBean.Status.PAUSED);
        MyDownLoadFileDao.getInstance(context.getApplicationContext()).updateDownloadFile(downLoadBean);
    }

    /**
     * 将已下载大小归零
     */
    protected synchronized void resetDownloadLength() {
        this.downloadLength = 0;
    }

    /**
     * 添加已下载大小
     * 多线程访问需加锁
     *
     * @param size
     */
    protected synchronized void updateDownloadLength(long size) {
        this.downloadLength += size;
        //通知更新界面
        int percent = (int) ((float) downloadLength * 100 / (float) fileLength);
        downLoadBean.setProgress(percent);
        if (percent == 100 || downloadLength == fileLength) {
            downLoadBean.setProgress(100); //上面计算有时候会有点误差，算到percent=99
            downLoadBean.setStatus(DownLoadBean.Status.FINISHED);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyDownLoadFileDao.getInstance(context.getApplicationContext()).updateDownloadFile(downLoadBean);
                }
            }).start();
        }
        Intent intent = new Intent(Constants.DOWNLOAD_MSG);
        if (downLoadBean.getStatus() == DownLoadBean.Status.WAITING) {
            Log.e("lym","status");
            downLoadBean.setStatus(DownLoadBean.Status.DOWNLOADING);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("downLoadBean", downLoadBean);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
        Log.e("lym", downLoadBean.getProgress() + "-------------");
    }

    protected String getDownloadPath() {
        return downloadPath;
    }

    private class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_LENGTH_SUCCESS:
                    beginDownload();
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
