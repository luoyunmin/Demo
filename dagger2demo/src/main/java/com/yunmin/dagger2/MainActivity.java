package com.yunmin.dagger2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.yunmin.dagger2.component.DaggerPresenterComponent;
import com.yunmin.dagger2.module.MainModule;
import com.yunmin.dagger2.presenter.MainPresenter;
import com.yunmin.dagger2.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.btn)
    Button btn;

    //;:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        newPresenter();
    }

    private void newPresenter() {
        DaggerPresenterComponent.builder().mainModule(new MainModule()).build().inject(this);
        mainPresenter.setView(this);
    }

    public void initView() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn)
    public void btnClick() {
        mainPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
