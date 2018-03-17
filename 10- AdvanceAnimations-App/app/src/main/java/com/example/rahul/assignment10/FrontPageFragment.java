package com.example.rahul.assignment10;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FrontPageFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn1;
    Button btn2;
    private CustomOnClickListener customOnClickListener;

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
        btn1 = (Button) rootView.findViewById(R.id.button);
        btn2 = (Button) rootView.findViewById(R.id.button2);
        customOnClickListener = (CustomOnClickListener) rootView.getContext();
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), Task1.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), v, "testAnimation");
                    getActivity().startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    public void buttonClicked(View v) {
        customOnClickListener.onClicked(v);
    }

    public interface CustomOnClickListener {
        void onClicked(View v);
    }
}
