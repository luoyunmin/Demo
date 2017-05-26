package com.yunmin.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyu on 2016/5/15.
 */
public class GalleryAdapter extends BaseAdapter {

    private List<String> strs;
    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
        strs = new ArrayList<>();
    }

    public GalleryAdapter(Context context, List<String> lists) {
        this.context = context;
        this.strs = lists;
    }

    @Override
    public int getCount() {
        return strs.size();
    }

    @Override
    public Object getItem(int i) {
        return strs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.gallery_adapter_item, viewGroup, false);
            holder.imageView = (ImageView) view.findViewById(R.id.img);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String imgUrl = (String) getItem(i);
        ImageLoader.getInstance().displayImage(imgUrl, holder.imageView);
        return view;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
