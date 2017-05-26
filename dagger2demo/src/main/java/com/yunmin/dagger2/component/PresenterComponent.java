package com.yunmin.dagger2.component;

import com.yunmin.dagger2.MainActivity;
import com.yunmin.dagger2.module.MainModule;

import dagger.Component;

/**
 * Created by luoyunmin on 2017/5/26.
 */

@Component(modules = MainModule.class)
public interface PresenterComponent {
    void inject(MainActivity activity);
}
