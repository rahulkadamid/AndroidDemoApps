package com.example.rahul.assignment6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutMeFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static AboutMeFragment newInstance(int sectionNumber)
    {
        AboutMeFragment fragment = new AboutMeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public AboutMeFragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.about_me_fragment, container, false);
        int option = getArguments().getInt(ARG_SECTION_NUMBER);
        return rootView;
    }
}
