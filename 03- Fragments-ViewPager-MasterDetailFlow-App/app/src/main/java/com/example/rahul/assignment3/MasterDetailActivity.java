package com.example.rahul.assignment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MasterDetailActivity extends AppCompatActivity implements MasterFragment.CustomOnClickMasterFragmentListener
{
    MovieData movieData;
    boolean mTwoPane = false;
    Fragment mcontent;
    public void onMasterFragmentButtonClicked (View v, int index)
    {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fT = fm.beginTransaction();

        if (mTwoPane == true)
        {

            fT.replace(R.id.masterslave, MovieFragment.newInstance(movieData.getItem(index)));
            fT.commit();
        }
        else
        {
            fT.replace(R.id.masterdetailframelayout, MovieFragment.newInstance(movieData.getItem(index)));
            fT.addToBackStack(null);
            fT.commit();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        movieData = new MovieData();
        setContentView(R.layout.activity_master_detail);

        if (savedInstanceState == null)
        {
            mcontent = MasterFragment.newInstance(R.id.masterfragmentlayout);
        }

        if( findViewById (R.id.masterslave) != null ) {
            mTwoPane = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.masterdetailframelayout,
                    mcontent).commit();
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.masterdetailframelayout,
                    mcontent).addToBackStack(null).commit();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mcontent", mcontent);
    }
}
