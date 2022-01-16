package com.tmdb_tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tmdb_tp.models.MovieModel;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView overview;
    TextView releaseDate;
    TextView genre_text;
    MovieModel movieModel;
    ArrayList<String> genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.setTitle("Movie Details");
        imageView = (ImageView) findViewById(R.id.movie_image);
        overview = (TextView) findViewById(R.id.overview);
        releaseDate = (TextView) findViewById(R.id.release_date);
        genre_text = (TextView) findViewById(R.id.genre);
        Intent intent = getIntent();
        movieModel = (MovieModel) intent.getSerializableExtra("movie");
        genres = intent.getStringArrayListExtra("genres");
        Glide.with(this.getApplicationContext())
                .load( "https://image.tmdb.org/t/p/original"
                        +movieModel.getPoster_path())
                .into(imageView);
        overview.setText(movieModel.getOverview());
        releaseDate.setText(movieModel.getRelease_date());
        String genre = new String("");
        for (String str: genres) {
            genre += str + "   ";
        }
        genre_text.setText(genre);
    }
}