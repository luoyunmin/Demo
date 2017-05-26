package com.yunmin.download;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoyu on 2016/4/16.
 */
public class DownLoadListAdapter extends BaseAdapter {
    Context context;
    List<DownLoadBean> downLoadBeanList;
    private Map<String, MyDownLoador> downloadorMap;

    public DownLoadListAdapter(Context context, List<DownLoadBean> downLoadBeans) {
        this.context = context;
        this.downLoadBeanList = downLoadBeans;
    }

    @Override
    public int getCount() {
        return downLoadBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return downLoadBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.download_list_item2, viewGroup, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.download_title);
            holder.time = (TextView) view.findViewById(R.id.download_date);
            holder.progress = (DownloadPercentView) view.findViewById(R.id.status_icon);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        initView(holder, i);
        return view;
    }

    private class ViewHolder {
        TextView title;
        TextView time;
        DownloadPercentView progress;
    }

    public void updateView(View view, int position) {
        if (null == view) return;
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.progress = (DownloadPercentView) view.findViewById(R.id.status_icon);
        holder.title = (TextView) view.findViewById(R.id.download_title);
        holder.time = (TextView) view.findViewById(R.id.download_date);
        initView(holder, position);
    }

    public void initView(ViewHolder holder, final int position) {
        DownLoadBean bean = downLoadBeanList.get(position);
        holder.title.setText(bean.getNtitle());
        holder.time.setText(bean.getNdate());
        setStatus(holder.progress, bean.getStatus());
        if (bean.getStatus() != DownLoadBean.Status.PENDING) {
            holder.progress.setProgress(bean.getProgress());
        }
        holder.progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownLoadBean bean = downLoadBeanList.get(position);
                if (downloadorMap != null && downloadorMap.containsKey(bean.getNurl())) {
                    downloadorMap.get(bean.getNurl()).pause();
                    downloadorMap.remove(bean.getNurl());
                    bean.setStatus(DownLoadBean.Status.PAUSED);
                    notifyDataSetChanged();
                } else {
                    MyDownLoador downloador = new MyDownLoador(context, bean);
                    downloador.download();
                    bean.setStatus(DownLoadBean.Status.WAITING);
                    notifyDataSetChanged();
                    if (downloadorMap == null) {
                        downloadorMap = new HashMap<String, MyDownLoador>();
                    }
                    downloadorMap.put(bean.getNurl(), downloador);
                }
                if (bean.getStatus() == DownLoadBean.Status.FINISHED) {
                    Log.e("lym", "下载完成");
                }
            }
        });
        if (bean.getStatus() != DownLoadBean.Status.PENDING) {
            holder.progress.setProgress(bean.getProgress());
        }
    }

    public void setStatus(DownloadPercentView view, DownLoadBean.Status status) {
        if (status == DownLoadBean.Status.PENDING) {
            view.setStatus(DownloadPercentView.STATUS_PEDDING);
        }
        if (status == DownLoadBean.Status.DOWNLOADING) {
            view.setStatus(DownloadPercentView.STATUS_DOWNLOADING);
        }
        if (status == DownLoadBean.Status.WAITING) {
            view.setStatus(DownloadPercentView.STATUS_WAITING);
        }
        if (status == DownLoadBean.Status.PAUSED) {
            view.setStatus(DownloadPercentView.STATUS_PAUSED);
        }
        if (status == DownLoadBean.Status.FINISHED) {
            view.setStatus(DownloadPercentView.STATUS_FINISHED);
        }
    }
}
