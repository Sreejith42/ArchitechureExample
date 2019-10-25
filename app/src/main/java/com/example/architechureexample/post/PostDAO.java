package com.example.architechureexample.post;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import retrofit2.Call;

@Dao

public interface PostDAO {

    @Insert
    void  insert(List<Post> post);



    @Query("DELETE FROM post_table")
    void  deleteAllNotes();

    @Query("SELECT * FROM  post_table ")

        //Check Updation and any updated or not LIVEDATA
    LiveData<List<Post>> getAllPosts();
}
