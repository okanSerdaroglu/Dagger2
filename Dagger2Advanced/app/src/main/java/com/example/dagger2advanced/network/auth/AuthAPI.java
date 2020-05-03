package com.example.dagger2advanced.network.auth;

import com.example.dagger2advanced.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthAPI {

    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);

}
