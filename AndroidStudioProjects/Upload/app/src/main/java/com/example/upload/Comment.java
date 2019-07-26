package com.example.upload;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private String name, email;

    private int postID, id;

    @SerializedName("body")
    private String text;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPostID() {
        return postID;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
