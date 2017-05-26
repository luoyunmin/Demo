package com.yunmin.retrofitdemo.API;

import com.yunmin.retrofitdemo.model.GitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by luoyunmin on 2017/3/29.
 */

public interface GitApi {

    //;:
    @GET("/users/{user}")
    Call<GitModel> getFeed(@Path("user") String user);
}
