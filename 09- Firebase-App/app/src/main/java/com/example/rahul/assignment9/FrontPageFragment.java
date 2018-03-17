package com.example.rahul.assignment9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FrontPageFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    public FrontPageFragment() {
    }

    public static FrontPageFragment newInstance(int sectionNumber) {
        FrontPageFragment fragment = new FrontPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.front_page_fragment, container, false);
        return rootView;
    }

}
