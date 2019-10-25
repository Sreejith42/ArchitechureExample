package com.example.architechureexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.architechureexample.post.Post;
import com.example.architechureexample.repository.NoteRepository;
import com.example.architechureexample.repository.PostRepository;
import com.example.architechureexample.repository.WebRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    private LiveData<List<Post>>mAllPosts;

    private PostRepository postRepository;

    private WebRepository webRepository;

    private final LiveData<List<Post>>  retroObservable;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository =new NoteRepository(application);
        allNotes=repository.getAllNotes();

        postRepository = new PostRepository(application);
        webRepository = new WebRepository(application);
        retroObservable = webRepository.getWebserviceResponseList();
        //postRoomDBRepository.insertPosts(retroObservable.getValue());
        mAllPosts = postRepository.getAllPosts();





    }

    public  void  insert(Note note){
        repository.insert(note);
    }

    public  void  update(Note note){
        repository.update(note);
    }

    public  void delete(Note note){
        repository.delete(note);
    }

    public  void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


    public LiveData<List<Post>> getAllPosts() {
        return mAllPosts;
    }
}
