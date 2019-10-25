package com.example.architechureexample.post;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Post.class,version = 2,exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {


   private   static PostDatabase instance;


    //access database
    public  abstract PostDAO postDAO();

    //syncronized means only a one thread can accesss at a time
    public  static synchronized PostDatabase getInstance(Context context){
        if (instance == null) {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    PostDatabase.class,"post_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }



    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PostDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };





    private  static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private PostDAO postDAO;


        private  PopulateDbAsyncTask(PostDatabase db){
            postDAO=db.postDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            postDAO.deleteAllNotes();
            return null;
        }
  }





}
