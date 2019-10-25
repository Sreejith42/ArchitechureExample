package com.example.architechureexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class,version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    //singleton class : create below so that we cannot make multiple instance and must be used same
    private  static  NoteDatabase instance;

    //access database
    public  abstract  NoteDAO noteDAO();

    //syncronized means only a one thread can accesss at a time
    public  static synchronized  NoteDatabase getInstance(Context context){
        if (instance == null) {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private  static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDAO noteDAO;


        private  PopulateDbAsyncTask(NoteDatabase db){
            noteDAO=db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Title1","desc1",1));
            noteDAO.insert(new Note("Title2","desc2",2));
            noteDAO.insert(new Note("Title3","desc3",3));
            return null;
        }
    }
}
