package com.example.rahul.assignment8;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class LoadMoviesActivity extends AppCompatActivity implements MyRecycleViewFragment.CustomOnClickRecycleViewListener {

    public void onRecycleViewItemClicked(HashMap<String, ?> movie) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.activity_load_movies, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_movies);
        setTitle("");
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ft = fmanager.beginTransaction();
        ft.add(R.id.activity_load_movies, MyRecycleViewFragment.newInstance(R.id.recyclerelativelayout));
        ft.addToBackStack(null);
        ft.commit();
    }
}
