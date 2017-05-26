package com.yunmin.dagger2.view;

import android.content.Context;

/**
 * Created by luoyunmin on 2017/5/25.
 */

public interface LoadDataView {
    /**
     * 显示隐藏加载的View
     */
    void showLoading();

    void hideLoading();

    /**
     * 显示错误
     * @param message
     */
    void showError(String message);

    Context getContext();
}
