package com.tmdb_tp.utils;

import com.tmdb_tp.models.ListOfGenres;
import com.tmdb_tp.models.ListOfMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {
    public static final String BASE_URL = Credentials.BASE_URL;

    public static final String API_KEY = Credentials.API_KEY;

    @GET("movie/popular?api_key="+ API_KEY +"&language=en-US")
    Call<ListOfMovies> listPopularMovies(
            @Query("page") int page
    );

    @GET("movie/upcoming?api_key="+ API_KEY +"&language=en-US")
    Call<ListOfMovies> listUpcomingMovies(
            @Query("page") int page
    );

    @GET("genre/movie/list?api_key="+ API_KEY +"&language=en-US")
    Call<ListOfGenres> listGenres();
}
