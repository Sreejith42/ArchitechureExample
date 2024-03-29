package com.example.architechureexample;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);


    @Query("DELETE FROM note_table")
     void  deleteAllNotes();


    @Query("SELECT * FROM  note_table ORDER BY priority DESC")

    //Check Updation and any updated or not LIVEDATA
    LiveData<List<Note>>getAllNotes();
}
