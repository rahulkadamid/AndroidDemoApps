package com.example.rahul.assignment3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MasterFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int currentIndex = 0;
    public interface CustomOnClickMasterFragmentListener {
        public void onMasterFragmentButtonClicked (View v, int index);
    }
    private CustomOnClickMasterFragmentListener customOnClickMfListener ;
    TextView txtview;
    Button btn4;
    Button btn5;
    public MasterFragment() {    }

    public static MasterFragment newInstance(int sectionNumber) {
        MasterFragment fragment = new MasterFragment();
        Bundle args = new Bundle();

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentposition");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
       super.onSaveInstanceState(outstate);
        outstate.putInt("currentposition", currentIndex);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master, container, false);
        txtview = (TextView)rootView.findViewById(R.id.textView8);
        Integer pos = currentIndex;
        String settxtviewstr = "" + pos.toString();
        txtview.setText(settxtviewstr);
        btn4 = (Button)rootView.findViewById(R.id.button4);
        btn5 = (Button)rootView.findViewById(R.id.button5);
        customOnClickMfListener = (CustomOnClickMasterFragmentListener)rootView.getContext();
        btn4.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                Integer i = currentIndex;
                if ( i < 29)
                {
                    i++;
                    currentIndex = i;
                    String setCurstr = "" + i.toString();
                    txtview.setText(setCurstr);
                }

                customOnClickMfListener.onMasterFragmentButtonClicked(v, i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener () {
            public void onClick (View v) {
                Integer i = currentIndex;
                if ( i > 0 )
                {
                    i--;
                    currentIndex = i;
                    String setCurStr = "" + i.toString();
                    txtview.setText(setCurStr);
                }
                customOnClickMfListener.onMasterFragmentButtonClicked(v, i);
            }
        });
        return rootView;
    }

}
