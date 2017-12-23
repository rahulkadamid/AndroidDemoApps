package com.example.rahul.assignment9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class Task3 extends AppCompatActivity implements MyRecycleViewFragment.CustomOnClickRecycleViewListener {
    Fragment mcontent;
    Toolbar toolbar;
    Toolbar toolbar4;

    public void onRecycleViewItemClicked(View v, HashMap<String, ?> movie) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();
        fT.replace(R.id.task3framelayout, MovieFragment.newInstance(movie));
        fT.addToBackStack(null);
        fT.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        if (savedInstanceState == null) {
            mcontent = MyRecycleViewFragment.newInstance(R.id.recyclerelativelayout);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.task3framelayout,
                mcontent).addToBackStack(null).commit();

        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        toolbar4.inflateMenu(R.menu.task3_bottom_toolbar_menu);
        setUpToolbarItemSelected();
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task3_top_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.task3_top_toolbar_menu_unhide) {
            toolbar4.setVisibility(View.VISIBLE);
        } else if (id == R.id.task3_top_toolbar_sort_by_name) {
            MyRecycleViewFragment mrv = (MyRecycleViewFragment) mcontent;
        }
        return true;
    }


    void setUpToolbarItemSelected() {
        toolbar4.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.task3_bottom_toolbar_sort_by_year) {
                    MyRecycleViewFragment mrv = (MyRecycleViewFragment) mcontent;
                }
                return false;
            }

        });
        toolbar4.setNavigationIcon(R.drawable.task3_navigation_icon);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar4.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }
}
