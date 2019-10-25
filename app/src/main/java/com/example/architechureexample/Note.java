package com.example.architechureexample;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

//part 1
//Room Annotation to create table
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private  int id;


    private String title;

    private String description;

    private int  priority;


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }
}
