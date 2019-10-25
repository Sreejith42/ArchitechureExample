package com.example.architechureexample.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.architechureexample.post.JsonPlaceHolder;
import com.example.architechureexample.post.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebRepository {

    Application application;
    final MutableLiveData<List<Post>> data = new MutableLiveData<>();
    private JsonPlaceHolder jsonPlaceHolder;

    public  WebRepository(Application application){
        this.application = application;
    }



    private static OkHttpClient providesOkHttpClientBuilder(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();

    }



    List<Post> webserviceResponseList = new ArrayList<>();


    public LiveData<List<Post>> getWebserviceResponseList() {



        String response ="";

        try {

            Gson gson = new GsonBuilder().serializeNulls().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


            jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

            getPosts();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return data;
    }



    private  void  getPosts(){



        Map<String,String> parameters = new HashMap<>();

        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");


        Call<List<Post>> call = jsonPlaceHolder.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                Log.d("Repository","Response::::"+response.body());


                if(!response.isSuccessful()){

                    return;
                }


                webserviceResponseList = response.body();

                Log.d("hello", "onResponse: "+webserviceResponseList);

                PostRepository postRepository = new PostRepository(application);

                postRepository.insertPost(webserviceResponseList);
                data.setValue(webserviceResponseList);
//
//                postRepository.getAllPosts().observe((LifecycleOwner) application, new Observer<List<Post>>() {
//                    @Override
//                    public void onChanged(List<Post> posts) {
//
//                    }
//                });




            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Log.d("Repository","Failed:::");


            }
        });
    }




    private List<Post> parseJson(List<Post> response) {

        List<Post> apiResults = new ArrayList<>();

        JSONObject jsonObject;

        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);


                Integer userId =object.getInt("userId");
                Integer id =object.getInt("id");
                String title= object.getString("title");
                String body = object.getString("body");


                Post post = new Post(userId,id,title,body);

                apiResults.add(post);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(getClass().getSimpleName(), String.valueOf(apiResults.size()));
        return apiResults;

    }

}
