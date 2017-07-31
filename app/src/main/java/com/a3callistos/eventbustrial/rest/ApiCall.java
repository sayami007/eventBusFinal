package com.a3callistos.eventbustrial.rest;

import android.app.usage.UsageEvents;
import android.util.Log;

import com.a3callistos.eventbustrial.app.Constant;
import com.a3callistos.eventbustrial.model.UserEntity;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bibesh on 7/31/17.
 */

public class ApiCall {

    private static ApiCall apiCall;
    private RestAPI restAPI;

    public ApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        restAPI = retrofit.create(RestAPI.class);
    }

    public static ApiCall getInstance() {
        if (apiCall == null) {
            apiCall = new ApiCall();
        }
        return apiCall;
    }

    public void getUsers() {
        restAPI.getUsers().enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    EventBus.getDefault().post(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.v("ERROR", "" + t.getMessage());
            }
        });
    }
}
