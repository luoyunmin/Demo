package com.yunmin.dagger2.api;

import com.yunmin.dagger2.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by luoyunmin on 2017/5/26.
 */

public interface MainService {
    @GET("users/{user}")
    Call<User> getUserInfo(@Path("user") String user);
}
