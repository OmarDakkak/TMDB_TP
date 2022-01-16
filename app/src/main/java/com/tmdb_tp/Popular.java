package com.tmdb_tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tmdb_tp.models.Genre;
import com.tmdb_tp.models.ListOfGenres;
import com.tmdb_tp.models.ListOfMovies;
import com.tmdb_tp.models.MovieModel;
import com.tmdb_tp.ui.MovieAdapter;
import com.tmdb_tp.utils.Credentials;
import com.tmdb_tp.utils.TmdbApi;

import java.util.ArrayList;
import java.util.List;

public class Popular extends AppCompatActivity {
    List<MovieModel> popularMovies = new ArrayList<>();
    List<Genre> listGenres = new ArrayList<>();
    TmdbApi tmdbApi;
    RecyclerView rvPopularMovies;
    int page = 1;
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        this.setTitle("Popular Movies");

        // Initialize and Assign Variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Popular Selected
        bottomNavigationView.setSelectedItemId(R.id.popular);

        // perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.popular:
                    return true;
                case R.id.upcoming:
                    startActivity(new Intent(getApplicationContext(), Upcoming.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
        tmdbApi = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbApi.class);

        tmdbApi.listGenres().enqueue(new Callback<ListOfGenres>(){

            @Override
            public void onResponse(Call<ListOfGenres> call, Response<ListOfGenres> response) {
                listGenres = response.body().getGenres();
            }

            @Override
            public void onFailure(Call<ListOfGenres> call, Throwable t) {

            }
        });

        tmdbApi.listPopularMovies(page).enqueue(new Callback<ListOfMovies>() {
            @Override
            public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                rvPopularMovies = findViewById(R.id.rv_popular_movies);
                popularMovies = response.body().getResults();
                MovieAdapter movieAdapter = new MovieAdapter(popularMovies, listGenres);
                rvPopularMovies.setAdapter(movieAdapter);
                rvPopularMovies.setLayoutManager(new GridLayoutManager(Popular.this, 2));

                // pagination
                rvPopularMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        if(!recyclerView.canScrollVertically(1)){
                            page++;
                            tmdbApi.listPopularMovies(page).enqueue(new Callback<ListOfMovies>() {
                                @Override
                                public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                                    rvPopularMovies = findViewById(R.id.rv_popular_movies);
                                    popularMovies = response.body().getResults();
                                    MovieAdapter movieAdapter = new MovieAdapter(popularMovies, listGenres);
                                    rvPopularMovies.setAdapter(movieAdapter);
                                    rvPopularMovies.setLayoutManager(new GridLayoutManager(Popular.this, 2));

                                    // pagination
                                    rvPopularMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                        @Override
                                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                            if(!recyclerView.canScrollVertically(1)){
                                                page++;
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<ListOfMovies> call, Throwable t) {

                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ListOfMovies> call, Throwable t) {

            }
        });


    }
}