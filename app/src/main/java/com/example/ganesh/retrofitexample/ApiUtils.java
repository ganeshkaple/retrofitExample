package com.example.ganesh.retrofitexample;

import com.example.ganesh.retrofitexample.models.remote.RetrofitClient;
import com.example.ganesh.retrofitexample.models.remote.SOService;

/**
 * Created by ganesh on 2/1/18.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
