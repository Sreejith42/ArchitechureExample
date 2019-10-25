package com.example.architechureexample.post;

import com.example.architechureexample.post.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<List<Post>> getPosts(
            @QueryMap Map<String,String> parameters
    );
}
