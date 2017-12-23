package com.example.rahul.assignment10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

public class Task1 extends AppCompatActivity implements MyRecycleViewFragment.CustomOnClickRecycleViewListener {
    Fragment mcontent;

    public void onRecycleViewItemClicked(View v, HashMap<String, ?> movie) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.task1framelayout, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
        if (savedInstanceState == null) {
            mcontent = MyRecycleViewFragment.newInstance(R.id.recyclerelativelayout);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.task1framelayout,
                mcontent).addToBackStack(null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }

}
