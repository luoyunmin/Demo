package com.yunmin.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunmin.retrofitdemo.API.GitApi;
import com.yunmin.retrofitdemo.model.GitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //;:

    Button btn_onclick;
    TextView tv;
    EditText edit_user;
    ProgressBar progressBar;
    String API = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_onclick = (Button) findViewById(R.id.btn_onclick);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.edit_user);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        Log.e("lym", "onCreate:" + Thread.currentThread().getName());
        btn_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edit_user.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                GitApi gitApi = retrofit.create(GitApi.class);
                Log.e("lym", "onClick:" + Thread.currentThread().getName());
                Call<GitModel> gitModelCall = gitApi.getFeed(user);
                gitModelCall.enqueue(new Callback<GitModel>() {
                    @Override
                    public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                        Log.e("lym", "onResponse:" + Thread.currentThread().getName());
                        GitModel gitModel = response.body();
                        tv.setText("Github Name:" + gitModel.getName()
                                + "\nblog:" + gitModel.getBlog()
                                + "\nid:" + gitModel.getId());
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<GitModel> call, Throwable t) {
                        Log.e("lym", "onFailure:" + Thread.currentThread().getName());
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });
    }
}
