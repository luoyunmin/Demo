package com.yunmin.download;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by luoyu on 2016/4/15.
 */
public class DownLoadBean implements Serializable, Parcelable {
    private int Nid;
    private String Ncontent;
    private String Ntitle;
    private String Nurl;
    private String Ndate;
    private int progress = 0;
    private Status status = Status.PENDING;

    protected DownLoadBean(int id, String title, String content, String url, String date) {
        this.Nid = id;
        this.Ntitle = title;
        this.Ncontent = content;
        this.Nurl = url;
        this.Ndate = date;
    }

    public static final Creator<DownLoadBean> CREATOR = new Creator<DownLoadBean>() {
        @Override
        public DownLoadBean createFromParcel(Parcel in) {
            int id = in.readInt();
            String title = in.readString();
            String content = in.readString();
            String url = in.readString();
            String date = in.readString();
            int progress = in.readInt();
            Status status = (Status) in.readValue(new ClassLoader() {
            });
            DownLoadBean downLoadBean = new DownLoadBean(id, title, content, url, date);
            downLoadBean.setProgress(progress);
            downLoadBean.setStatus(status);
            return downLoadBean;
        }

        @Override
        public DownLoadBean[] newArray(int size) {
            return new DownLoadBean[size];
        }
    };

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    public String getNcontent() {
        return Ncontent;
    }

    public void setNcontent(String ncontent) {
        Ncontent = ncontent;
    }

    public String getNtitle() {
        return Ntitle;
    }

    public void setNtitle(String ntitle) {
        Ntitle = ntitle;
    }

    public String getNurl() {
        return Nurl;
    }

    public void setNurl(String nurl) {
        Nurl = nurl;
    }

    public String getNdate() {
        return Ndate;
    }

    public void setNdate(String ndate) {
        Ndate = ndate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Nid);
        parcel.writeString(Ntitle);
        parcel.writeString(Ncontent);
        parcel.writeString(Nurl);
        parcel.writeString(Ndate);
        parcel.writeInt(progress);
        parcel.writeValue(status);
    }

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
