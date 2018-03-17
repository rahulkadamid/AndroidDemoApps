package com.example.rahul.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  Fragment1.CustomOnClickListener
{
    Intent intent2;
    Intent intent3;
    public void onClicked (View v)
    {
        FragmentManager fm = getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.aboutMe:
                FragmentTransaction fT = fm.beginTransaction();
                fT.replace(R.id.frontpagefragment, AboutMeFragment.newInstance(R.id.aboutmelayout));
                fT.addToBackStack(null);
                fT.commit();
                break;
            case R.id.masterDetail:
                intent2 = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent2);
                break;
            case R.id.viewPage:
                intent3 = new Intent(MainActivity.this, MasterDetailActivity.class);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction ();
        fragmentTransaction.replace(R.id.frontpagefragment, Fragment1.newInstance(R.id.layout1));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.AboutMe) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction ();
            fragmentTransaction.replace(R.id.frontpagefragment, AboutMeFragment.newInstance(R.id.aboutmelayout));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (id == R.id.task2)
        {
            intent2 = new Intent(MainActivity.this, ViewPagerActivity.class);
            startActivity(intent2);
        }
        if (id == R.id.task3)
        {
            intent3 = new Intent(MainActivity.this, MasterDetailActivity.class);
            startActivity(intent3);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onCreate(savedInstanceState);
    }
}
