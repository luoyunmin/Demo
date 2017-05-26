package com.yunmin.dagger2.presenter;

import com.yunmin.dagger2.api.MainService;
import com.yunmin.dagger2.view.MainView;

/**
 * Created by luoyunmin on 2017/5/25.
 */

public interface MainPresenter extends Presenter {

    void setView(MainView mainView);

    void setServer(MainService mainServer);

    void getData();

}
