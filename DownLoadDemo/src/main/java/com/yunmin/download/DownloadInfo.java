package com.yunmin.download;

/**
 * @Class: DownloadInfo
 * @Description: ����״̬��Ϣ
 * @author: lling(www.cnblogs.com/liuling)
 * @Date: 2015/10/13
 */
public class DownloadInfo {

    private String url;  //���ص�����

    //�������ص�AsyncTask�ı�ţ�һ���ļ���3���߳����أ���ŷֱ�Ϊ0,��1��2
    private int taskId;

    //��¼���߳��Ѿ����صĳ���
    private long downloadLength;

    //��ʶ���̵߳����������Ƿ����
    private int downloadSuccess;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public long getDownloadLength() {
        return downloadLength;
    }

    public void setDownloadLength(long downloadLength) {
        this.downloadLength = downloadLength;
    }

    public int isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(int downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }
}
