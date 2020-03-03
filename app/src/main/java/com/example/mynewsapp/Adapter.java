package com.example.mynewsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.loader.content.Loader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends ArrayAdapter<News> {


    public Adapter(@NonNull Context context, @NonNull List<News> newsArticles) {

        super(context, 0, newsArticles);

    }


    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,

        // otherwise, if convertView is null, then inflate a new list item layout.

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.activity_list_item, parent, false);

        }


        // Find the article at the given position in the list of articles

        News currentNewsArticle = getItem(position);


        // Get and display the article's Section

        String newsSection = currentNewsArticle.getSection();

        TextView sectionNameView = listItemView.findViewById(R.id.selection);

        sectionNameView.setText(newsSection);


        // Get and display the article's Title

        String newsTitle = currentNewsArticle.getTitle();

        TextView titleView = listItemView.findViewById(R.id.artecte_name);

        titleView.setText(newsTitle);



        // Get and display the article's Author

        String newsAuthor = currentNewsArticle.getAuthor() + " ";

        TextView authorView = listItemView.findViewById(R.id.auther);

        authorView.setText(newsAuthor);


        // Find and display the article's Thumbnail

        Bitmap newsPhoto = currentNewsArticle.getmImage();

        ImageView photoView = listItemView.findViewById(R.id.article_image);

        if (newsPhoto != null && Loader.mPrefThumbnail) {

            // If photo available or Thumbnail preference is true


            // Set Title maxlines and minlines to 3

            titleView.setMaxLines(3);

            titleView.setMinLines(3);


            // target and increase the aspect-ratio for photoView to 16:9

            ConstraintLayout constraintLayout = listItemView.findViewById(R.id.news_list);

            ConstraintSet set = new ConstraintSet();

            set.clone(constraintLayout);

            set.setDimensionRatio(photoView.getId(), "16:9");

            set.applyTo(constraintLayout);

            photoView.setImageBitmap(newsPhoto);


            // To clip the end of the article_title to end of the article_image (thumbnail):

            // First break the existing constraint connection.

            set.clear(R.id.artecte_name, ConstraintSet.START);

            // Then attach a new constraint connection.

            set.connect(R.id.artecte_name, ConstraintSet.END, R.id.article_image, ConstraintSet.END, 0);

            set.applyTo(constraintLayout);


        } else {

            // Remove photo and change layout:

            // Increase Title maxlines and minlines from 3 to 4

            titleView.setMaxLines(4);

            titleView.setMinLines(4);


            // target and decrease the aspect-ratio for photoView to 16:4

            ConstraintLayout constraintLayout = listItemView.findViewById(R.id.news_list);

            ConstraintSet set = new ConstraintSet();

            set.clone(constraintLayout);

            set.setDimensionRatio(photoView.getId(), "16:4");

            set.applyTo(constraintLayout);

            // set background color in lieu of thumbnail image

            photoView.setImageResource(R.drawable.mm);

            photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            // To clip the end of article_title to start of article_time (bookmark):

            // First break the constraint connection.

            set.clear(R.id.artecte_name, ConstraintSet.END);

            // Then attach a new constraint connection.


            set.connect(R.id.artecte_name, ConstraintSet.START, R.id.article_image, ConstraintSet.START, 0);

            set.applyTo(constraintLayout);

        }


        // Return the list item view that is now showing the appropriate data

        return listItemView;

    }


    /**
     * Return a formatted time string (i.e. "Mar 3, '18") from a Date object.
     */

    private String formatDate(String date) {

        final SimpleDateFormat inputParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());


        Date date_out = null;

        try {

            date_out = inputParser.parse(date);

        } catch (final ParseException e) {

            e.printStackTrace();

        }

        final SimpleDateFormat outputFormatter = new SimpleDateFormat("MMM dd ''yy", Locale.US);

        return outputFormatter.format(date_out);

    }


    /**
     * Return a formatted date string (i.e. "4:30 PM") from a Date object.
     */

    private String formatTime(String date) {

        final SimpleDateFormat inputParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());


        Date date_out = null;

        try {

            date_out = inputParser.parse(date);

        } catch (final ParseException e) {

            e.printStackTrace();

        }

        final SimpleDateFormat outputFormatter = new SimpleDateFormat("h:mm a", Locale.US);

        return outputFormatter.format(date_out);

    }
}




