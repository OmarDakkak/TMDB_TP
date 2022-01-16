package com.tmdb_tp.models;

import java.io.Serializable;
import java.util.List;

public class MovieModel implements Serializable {
    private String title;
    private String poster_path;
    private String release_date;
    private int id;
    private String overview;
    List<Integer> genre_ids;

    // constructor

    public MovieModel(int id, String title, String poster_path, String release_date, String overview, List<Integer> genre_ids) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.overview = overview;
        this.genre_ids = genre_ids;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}
