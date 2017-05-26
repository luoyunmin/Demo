package com.yunmin.statuslayout;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewStub;

/**
 * Created by luoyunmin on 2017/3/6.
 */

public final class Builder {

    private Context context;
    private int loadingLayoutResId;//等待加载xml文件
    private int contentLayoutResId;//显示内容xml文件
    private ViewStub netWorkErrorVs;
    private int netWorkErrorRetryViewId;
    private ViewStub emptyDataVs;
    private int emptyDataRetryViewId;
    private ViewStub errorVs;
    private int errorRetryViewId;
    private int retryViewId;
    private OnShowHideViewListener onShowHideViewListener;
    private OnRetryListener onRetryListener;

    public Builder(Context context) {
        this.context = context;
    }

    public Builder loadingView(@LayoutRes int loadingLayoutResId) {
        this.loadingLayoutResId = loadingLayoutResId;
        return this;
    }

    public Builder netWorkErrorView(@LayoutRes int netWorkErrorId) {
        netWorkErrorVs = new ViewStub(context);
        netWorkErrorVs.setLayoutResource(netWorkErrorId);
        return this;
    }

    public Builder emptyDataView(@LayoutRes int noDataViewId) {
        emptyDataVs = new ViewStub(context);
        emptyDataVs.setLayoutResource(noDataViewId);
        return this;
    }

    public Builder errorView(@LayoutRes int errorViewId) {
        errorVs = new ViewStub(context);
        errorVs.setLayoutResource(errorViewId);
        return this;
    }

    public Builder contentView(@LayoutRes int contentLayoutResId) {
        this.contentLayoutResId = contentLayoutResId;
        return this;
    }

    public Builder netWorkErrorRetryViewId(@LayoutRes int netWorkErrorRetryViewId) {
        this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
        return this;
    }

    public Builder emptyDataRetryViewId(int emptyDataRetryViewId) {
        this.emptyDataRetryViewId = emptyDataRetryViewId;
        return this;
    }

    public Builder errorRetryViewId(int errorRetryViewId) {
        this.errorRetryViewId = errorRetryViewId;
        return this;
    }

    public Builder retryViewId(int retryViewId) {
        this.retryViewId = retryViewId;
        return this;
    }
}
