package com.example.architechureexample.post;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post_table")
public class Post {

    public Integer getSimpleId() {
        return simpleId;
    }

    public void setSimpleId(Integer simpleId) {
        this.simpleId = simpleId;
    }

    @PrimaryKey(autoGenerate = true)
    private Integer simpleId ;

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose

    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public Post(Integer id, int userId, String title, String body) {
        this.id =id;
        this.userId=userId;
        this.title=title;
        this.body=body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
