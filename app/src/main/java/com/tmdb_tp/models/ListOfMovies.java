package com.tmdb_tp.models;

import java.util.List;

public class ListOfMovies {
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        String out="";
        for (MovieModel v: results) {
            out+=" " + v.getTitle();
        }
        return out;
    }
}

