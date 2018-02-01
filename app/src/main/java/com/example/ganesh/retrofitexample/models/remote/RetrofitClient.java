package com.example.ganesh.retrofitexample.models.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganesh on 2/1/18.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //Trying RxJava Bitch,Failed
              //  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
