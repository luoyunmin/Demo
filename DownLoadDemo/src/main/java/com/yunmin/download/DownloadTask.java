package com.yunmin.download;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;

/**
 * @Class: DownloadTask
 * @Description: �ļ�����AsyncTask
 * @author: lling(www.cnblogs.com/liuling)
 * @Date: 2015/10/13
 */
public class DownloadTask extends AsyncTask<String, Integer , Long> {
    private static final String TAG = "DownloadTask";

    private int taskId;
    private long beginPosition;
    private long endPosition;
    private long downloadLength;
    private String url;
    private Downloador downloador;
    private DownloadInfoDAO downloadInfoDAO;


    public DownloadTask(int taskId, long beginPosition, long endPosition, Downloador downloador,
                        Context context) {
        this.taskId = taskId;
        this.beginPosition = beginPosition;
        this.endPosition = endPosition;
        this.downloador = downloador;
        downloadInfoDAO = DownloadInfoDAO.getInstance(context.getApplicationContext());
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "onPreExecute");
    }

    @Override
    protected void onPostExecute(Long aLong) {
        Log.e(TAG, url + "taskId:" + taskId + "executed");
//        downloador.updateDownloadInfo(null);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //֪ͨdownloador���������ش�С
//        downloador.updateDownloadLength(values[0]);
    }

    @Override
    protected void onCancelled() {
        Log.e(TAG, "onCancelled");
//        downloador.updateDownloadInfo(null);
    }

    @Override
    protected Long doInBackground(String... params) {
        //������жϵ������ǣ���������ڵȴ�����ͣ�ˣ����е������Ѿ�cancel�ˣ���ֱ���˳�
        if(isCancelled()) {
            return null;
        }
        url = params[0];
        if(url == null) {
            return null;
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        InputStream is;
        RandomAccessFile fos = null;
        OutputStream output = null;

        DownloadInfo downloadInfo = null;
        try {
            //�����ļ�
            File file = new File(downloador.getDownloadPath() + File.separator + url.substring(url.lastIndexOf("/") + 1));

            //��ȡ֮ǰ���ر������Ϣ
            downloadInfo = downloadInfoDAO.getDownloadInfoByTaskIdAndUrl(taskId, url);
            //��֮ǰ������λ�ü�������
            //��������ж�file.exists()���ж��Ƿ��û�ɾ���ˣ�����ļ�û�������꣬�����Ѿ����û�ɾ���ˣ�����������
            if(file.exists() && downloadInfo != null) {
                if(downloadInfo.isDownloadSuccess() == 1) {
                    //�������ֱ�ӽ���
                    return null;
                }
                beginPosition = beginPosition + downloadInfo.getDownloadLength();
                downloadLength = downloadInfo.getDownloadLength();
            }
            if(!file.exists()) {
                //�����task�Ѿ������꣬�����ļ����û�ɾ��������Ҫ�������������س��ȣ���������
                downloador.resetDownloadLength();
            }

            //�������ص�����λ��beginPosition�ֽڵ�endPosition�ֽ�
            Header header_size = new BasicHeader("Range", "bytes=" + beginPosition + "-" + endPosition);
            request.addHeader(header_size);
            //ִ�������ȡ����������
            response = client.execute(request);
            is = response.getEntity().getContent();

            //�����ļ������
            fos = new RandomAccessFile(file, "rw");
            //���ļ���size�Ժ��λ�ÿ�ʼд��
            fos.seek(beginPosition);

            byte buffer [] = new byte[1024];
            int inputSize = -1;
            while((inputSize = is.read(buffer)) != -1) {
                fos.write(buffer, 0, inputSize);
                downloadLength += inputSize;
                downloador.updateDownloadLength(inputSize);

                //�����ͣ�ˣ���Ҫ��������Ϣ�������ݿ�
                if (isCancelled()) {
                    if(downloadInfo == null) {
                        downloadInfo = new DownloadInfo();
                    }
                    downloadInfo.setUrl(url);
                    downloadInfo.setDownloadLength(downloadLength);
                    downloadInfo.setTaskId(taskId);
                    downloadInfo.setDownloadSuccess(0);
                    //����������Ϣ�����ݿ�
                    downloadInfoDAO.insertDownloadInfo(downloadInfo);
                    return null;
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally{
            try{
                if (request != null) {
                    request.abort();
                }
                if(output != null) {
                    output.close();
                }
                if(fos != null) {
                    fos.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        //ִ�е����˵����task�Ѿ���������
        if(downloadInfo == null) {
            downloadInfo = new DownloadInfo();
        }
        downloadInfo.setUrl(url);
        downloadInfo.setDownloadLength(downloadLength);
        downloadInfo.setTaskId(taskId);
        downloadInfo.setDownloadSuccess(1);
        //����������Ϣ�����ݿ�
        downloadInfoDAO.insertDownloadInfo(downloadInfo);
        return null;
    }
}
