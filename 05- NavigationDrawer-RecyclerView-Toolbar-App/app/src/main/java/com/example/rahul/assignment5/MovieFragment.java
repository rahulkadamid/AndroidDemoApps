package com.example.rahul.assignment5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MovieFragment extends Fragment {
    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    ShareActionProvider mshareActionProvider;
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
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.findItem(R.id.movie_fragment_menu) == null) {
            inflater.inflate(R.menu.movie_fragment_toolbar, menu);
        }
        MenuItem menuItem1 = menu.findItem(R.id.movie_fragment_toolbar_imageview);
        MenuItem menuItem2 = menu.findItem(R.id.movie_fragment_toolbar_title);
        MenuItem menuItem3 = menu.findItem(R.id.movie_fragment_action_provider);
        if (menuItem1 != null) {
            Integer k = (Integer)movie.get("image");
            menuItem1.setIcon(k);
        }
        if (menuItem2 != null) {
            menuItem2.setTitle(movie.get("title").toString());
        }
        if (menuItem3 != null) {
            mshareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem3);
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_TEXT, (String) movie.get("title"));
            mshareActionProvider.setShareIntent(intentShare);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.movie_fragment_toolbar_title) {
        }
        return super.onOptionsItemSelected(item);
    }
}
