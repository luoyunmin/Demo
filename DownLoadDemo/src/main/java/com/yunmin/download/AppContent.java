package com.yunmin.download;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @Class: AppContent
 * @Description: Ӧ���г�app����
 * @author: lling(www.cnblogs.com/liuling)
 * @Date: 2015/10/13
 */
public class AppContent implements Serializable, Parcelable {
    //Ӧ������
    private String name;
    //Ӧ����������
    private String url;

    private int downloadPercent = 0;

    private Status status = Status.PENDING;

    public AppContent(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDownloadPercent() {
        return downloadPercent;
    }

    public void setDownloadPercent(int downloadPercent) {
        this.downloadPercent = downloadPercent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //ʵ��Parcel�ӿڱ��븲��ʵ�ֵķ���
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /*��AppContent�ĳ�Աд��Parcel��
         * ע��Parcel�е������ǰ�˳��д��Ͷ�ȡ�ģ����ȱ�д��ľͻ��ȱ���ȡ����
         */
        dest.writeString(name);
        dest.writeString(url);
        dest.writeInt(downloadPercent);
        dest.writeValue(status);
    }

    //�þ�̬���Ǳ���Ҫ�еģ��������ֱ�����CREATOR����������
    public static final Creator<AppContent> CREATOR =
            new Creator<AppContent>() {

                @Override
                public AppContent createFromParcel(Parcel source) {
                    //��Parcel��ȡͨ��writeToParcel����д���AppContent����س�Ա��Ϣ
                    String name = source.readString();
                    String url = source.readString();
                    int downloadPercent = source.readInt();
                    Status status = (Status)source.readValue(new ClassLoader(){});
                    AppContent appContent = new AppContent(name, url);
                    appContent.setDownloadPercent(downloadPercent);
                    appContent.setStatus(status);
                    //���Ӷ�ȡ������Ϣ����������Person����
                    return appContent;
                }

                @Override
                public AppContent[] newArray(int size)
                {
                    // TODO Auto-generated method stub
                    //����AppContent��������
                    return new AppContent[size];
                }
            };

    public enum Status {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING(1),
        /**
         * Indicates that the task is wating.
         */
        WAITING(2),
        /**
         * Indicates that the task is downloading.
         */
        DOWNLOADING(3),

        /**
         * Indicates that the task was paused.
         */
        PAUSED(4),

        /**
         * Indicates that the task has finished.
         */
        FINISHED(5);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Status getByValue(int value) {
            switch (value) {
                case 1:
                    return Status.PENDING;
                case 2:
                    return Status.WAITING;
                case 3:
                    return Status.DOWNLOADING;
                case 4:
                    return Status.PAUSED;
                case 5:
                    return Status.FINISHED;
            }
            return Status.PENDING;
        }
    }
}
