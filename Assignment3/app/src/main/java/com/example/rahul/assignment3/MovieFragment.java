package com.example.rahul.assignment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

public class MovieFragment extends Fragment
{
    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    private int total = 0;
    TextView Title;
    TextView Description;
    TextView StarInfo;
    ImageView image;
    TextView YearInfo;
    RatingBar rating;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            movie = (HashMap<String, ?>)getArguments().getSerializable(ARG_MOVIE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.moviefragment, container, false);
        Title = (TextView)rootView.findViewById(R.id.textView3);
        Description = (TextView)rootView.findViewById(R.id.textView4);
        image = (ImageView)rootView.findViewById(R.id.imageView2);
        YearInfo = (TextView)rootView.findViewById(R.id.textView5);
        StarInfo = (TextView)rootView.findViewById(R.id.textView6);
        rating = (RatingBar)rootView.findViewById(R.id.ratingBar);
        rating.setMax(5);
        rating.setStepSize(0.1f);
        Title.setText(movie.get("name").toString());
        Description.setText(movie.get("description").toString());
        Integer k = (Integer)movie.get("image");
        image.setImageResource(k);
        YearInfo.setText("Year: "+movie.get("year").toString());
        StarInfo.setText("Stars: "+movie.get("stars").toString());
        Double frat = (Double)movie.get("rating");
        float ft = (float) (frat.floatValue())/2.0f;
        rating.setRating((float)ft);
        return rootView;
    }
}
