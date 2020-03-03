package com.example.mynewsapp;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class Loader extends AsyncTaskLoader<List<News>> {



    /** Query URL */

    private String mUrl;

    public static boolean mPrefThumbnail;



    public Loader(Context context, String url, Boolean prefThumbnail) {

        super(context);

        mUrl = url;

        mPrefThumbnail = prefThumbnail;

    }



    @Override

    protected void onStartLoading() {

        forceLoad();

    }



    /**

     * This is on a background thread.

     */

    @Override

    public List<News> loadInBackground() {

        if (mUrl == null) {

            return null;

        }



        // Perform the network request, parse the response, and extract a list of newsArticles.

        List<News> newsArticles = QueryUtils.fetchArticleData(mUrl);

        return newsArticles;

    }

}