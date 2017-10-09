package com.example.rahul.assignment3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn1;
    Button btn2;
    Button btn3;
    public interface CustomOnClickListener {
        public void onClicked (View v);
    }
    private CustomOnClickListener customOnClickListener ;
    public static Fragment1 newInstance(int sectionNumber)
    {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment1()
    {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        btn1 = (Button)rootView.findViewById(R.id.aboutMe);
        btn2 = (Button)rootView.findViewById(R.id.masterDetail);
        btn3 = (Button)rootView.findViewById(R.id.viewPage);
        customOnClickListener = (CustomOnClickListener)rootView.getContext();
        btn1.setOnClickListener(new View.OnClickListener () {
                           public void onClick (View v) {
                                buttonClicked(v);
                           }
                       });
        btn2.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                buttonClicked(v);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return rootView;
    }
    public void buttonClicked ( View v ) {
        customOnClickListener.onClicked (v);
    }

}
