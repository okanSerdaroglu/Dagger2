package com.example.dagger2advanced.network.main;

import com.example.dagger2advanced.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    // posts?userID = 1
    @GET("posts")
    Flowable<List<Post>> getPostList(@Query("userId") int id);

}
