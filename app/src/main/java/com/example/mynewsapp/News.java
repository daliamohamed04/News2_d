package com.example.mynewsapp;

import android.graphics.Bitmap;

public class News {

    private String mTitle;
    private String mAuthor;
    private String mSection;
    private Bitmap mImage;



    public News(String title, String author, String section,Bitmap image) {

        this.mTitle = title;
        this.mAuthor = author;
        this.mSection = section;
        this.mImage = image;

    }

    public String getTitle() {
        return mTitle;
    }


    public String getAuthor() {
        return mAuthor;
    }


    public String getSection() {
        return mSection;
    }

    public Bitmap getmImage() {
        return mImage;
    }
}

