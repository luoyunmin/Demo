package com.yunmin.dagger2.module;

import com.yunmin.dagger2.presenter.MainPresenter;
import com.yunmin.dagger2.presenter.impl.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luoyunmin on 2017/5/26.
 */
@Module
public class MainModule {
    @Provides
    MainPresenter providesMainPresenterImpl() {
        return new MainPresenterImpl();
    }
}
