package com.example.rahul.assignment8;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDataJson {

    public static final String PHP_SERVER = "http://rahul-kadam.com/";
    List<Map<String, ?>> moviesList;

    public int getSize() {
        return moviesList.size();
    }

    public MovieDataJson() {
        moviesList = new ArrayList<Map<String, ?>>();
    }

    public void downloadMovieDataJson(String url) {
        String jsondata = MyUtility.downloadJSONusingHTTPGetRequest(url);
        try {
            JSONArray arr = new JSONArray(jsondata);
            moviesList.clear();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonobj = arr.getJSONObject(i);
                moviesList.add(createMovie(jsonobj.getString("MovieId"), jsonobj.getString("Title"),
                        jsonobj.getString("VoteCnt"),jsonobj.getString("VoteAvg"),jsonobj.getString("Popularity"),
                        jsonobj.getString("Poster"), jsonobj.getString("Backdrop"),
                        jsonobj.getString("Overview"),jsonobj.getString("ReleaseDay") ));
            }
        } catch (Exception e) {

        }
    }

    private HashMap createMovie(String MovieId, String Title, String VoteCnt,String VoteAvg,String Popularity,
                                String Poster, String Backdrop,
                                String Overview, String ReleaseDay) {
        HashMap movie = new HashMap();
        movie.put("MovieId", MovieId);
        movie.put("Title", Title);
        movie.put("VoteCnt", VoteCnt);
        movie.put("VoteAvg", VoteAvg);
        movie.put("Popularity", Popularity);
        movie.put("Poster", Poster);
        movie.put("Backdrop", Backdrop);
        movie.put("Overview", Overview);
        movie.put("ReleaseDay", ReleaseDay);
        movie.put("selection", false);
        return movie;
    }

    public HashMap getItem(int i) {
        if (i >= 0 && i < moviesList.size()) {
            return (HashMap) moviesList.get(i);
        } else return null;
    }

    public void addItem(int position, Map<String, ?> item) {
        final JSONObject json;
        if (item != null) {
            json = new JSONObject(item);
        } else
            json = null;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String url = PHP_SERVER + "add";
                MyUtility.sendHttPostRequest(url, json);
            }
        };
        new Thread(runnable).start();
        moviesList.add(position, item);
    }

    public void deleteItem(int position, final Map<String, ?> item) {
        final JSONObject json;
        if (item != null) {
            json = new JSONObject(item);
        } else
            json = null;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String url = PHP_SERVER + "delete";
                MyUtility.sendHttPostRequest(url, json);
            }
        };
        new Thread(runnable).start();
        moviesList.remove(position);
    }
}
