package com.example.rahul.assignment8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieData {

    List<Map<String, ?>> moviesList;

    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public int getSize() {
        return moviesList.size();
    }

    public HashMap getItem(int i) {
        if (i >= 0 && i < moviesList.size()) {
            return (HashMap) moviesList.get(i);
        } else return null;
    }

    public MovieData() {
        moviesList = new ArrayList<Map<String, ?>>();
    }

    public void addItem(int position, Map<String, ?> map) {
        moviesList.add(position, map);
    }

}
