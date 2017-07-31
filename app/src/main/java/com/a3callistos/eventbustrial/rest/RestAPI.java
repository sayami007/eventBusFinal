package com.a3callistos.eventbustrial.rest;

import com.a3callistos.eventbustrial.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bibesh on 7/31/17.
 */

public interface RestAPI {
    @GET("/users/sayami007")
    Call<UserEntity> getUsers();
}
