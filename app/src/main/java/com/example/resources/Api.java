package com.example.resources;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("/register")
    Call<AuthModel> register(@Body RegisterAPI registerAPI);

    @POST("/login")
    Call<AuthModel> login(@Body LoginAPI loginAPI);
}
