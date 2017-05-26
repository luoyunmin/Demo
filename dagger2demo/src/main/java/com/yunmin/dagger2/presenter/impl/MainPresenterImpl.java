package com.yunmin.dagger2.presenter.impl;

import com.yunmin.dagger2.model.User;
import com.yunmin.dagger2.presenter.MainPresenter;
import com.yunmin.dagger2.api.MainService;
import com.yunmin.dagger2.view.MainView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luoyunmin on 2017/5/25.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView mainView;
    MainService mainService;

    @Inject
    public MainPresenterImpl() {
    }

    @Override
    public void getData() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainService mainService = retrofit.create(MainService.class);
        Call<User> call = mainService.getUserInfo("luoyunmin");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (mainView != null) {
                    mainView.showToast(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void setServer(MainService mainServer) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }
}
