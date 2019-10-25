package com.example.architechureexample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.architechureexample.Note;
import com.example.architechureexample.post.JsonPlaceHolder;
import com.example.architechureexample.NoteDatabase;
import com.example.architechureexample.post.Post;
import com.example.architechureexample.post.PostDAO;
import com.example.architechureexample.post.PostDatabase;
import com.example.architechureexample.service.RetrofitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {


    private PostDAO postDAO;
    private LiveData<List<Post>> allPosts;
//    private JsonPlaceHolder jsonPlaceHolder;

    public PostRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        PostDatabase postDatabase =PostDatabase.getInstance(application);


        postDAO =postDatabase.postDAO();
        allPosts =postDAO.getAllPosts();


//        jsonPlaceHolder = RetrofitService.createService(JsonPlaceHolder.class);
    }



    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }




    public void insertPost (List<Post> posts) {
        new InsertPostAsyncTask(postDAO).execute(posts);
    }








    private static  class InsertPostAsyncTask extends AsyncTask<List<Post>,Void,Void> {
        private PostDAO postDAO;

        private  InsertPostAsyncTask(PostDAO postDAO){
            this.postDAO=postDAO;
        }



        @Override
        protected Void doInBackground(List<Post>... lists) {


                postDAO.insert(lists[0]);




            return null;
        }
    }




}
