package com.example.resources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance = null;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl("https://198.168.21.105:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}

