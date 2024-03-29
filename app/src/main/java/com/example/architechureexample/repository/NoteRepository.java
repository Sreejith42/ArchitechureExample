package com.example.architechureexample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.architechureexample.Note;
import com.example.architechureexample.NoteDAO;
import com.example.architechureexample.NoteDatabase;
import com.example.architechureexample.post.PostDatabase;

import java.util.List;


public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;



    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        PostDatabase postDatabase =PostDatabase.getInstance(application);



        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();


    }
















    // room doesnot allow databse class on thread class

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDAO).execute(note);

    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDAO).execute(note);

    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDAO).execute(note);

    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDAO).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }















    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;

        private InsertNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }







    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;

        private UpdateNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }







    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO noteDAO;

        private DeleteNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }










    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDAO noteDAO;

        private DeleteAllNoteAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAllNotes();
            return null;
        }
    }


}



