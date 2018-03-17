package com.example.rahul.assignment4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MovieFragment extends Fragment{
    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    private int total = 0;
    TextView Title;
    TextView Description;
    ImageView image;
    TextView YearInfo;
    public static MovieFragment newInstance(HashMap<String, ?> movie)
    {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }
    public MovieFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            movie = (HashMap<String, ?> )getArguments().getSerializable(ARG_MOVIE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.moviefragment, container, false);
        Title = (TextView)rootView.findViewById(R.id.movietitle);
        Description = (TextView)rootView.findViewById(R.id.moviedescription);
        image = (ImageView)rootView.findViewById(R.id.movieimageView);
        YearInfo = (TextView)rootView.findViewById(R.id.movieyear);
        Title.setText(movie.get("title").toString());
        Description.setText(movie.get("overview").toString());
        Integer k = (Integer)movie.get("image");
        image.setImageResource(k);
        YearInfo.setText("Release: "+movie.get("release").toString());
        return rootView;
    }
}