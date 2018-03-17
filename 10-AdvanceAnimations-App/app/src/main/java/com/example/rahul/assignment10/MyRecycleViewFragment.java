package com.example.rahul.assignment10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class MyRecycleViewFragment extends Fragment {
    RecyclerView mRecycleView;
    MyRecycleViewAdapter mRecycleViewAdapter;
    MovieData moviedata;
    Button selectall;
    Button clearall;
    Button delete;
    Button sort;
    private CustomOnClickRecycleViewListener customOnClickRvListener;

    public MyRecycleViewFragment() {
    }

    public static MyRecycleViewFragment newInstance(int section_number) {
        MyRecycleViewFragment mr = new MyRecycleViewFragment();
        return mr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();
        selectall = (Button) rootview.findViewById(R.id.selectall);
        clearall = (Button) rootview.findViewById(R.id.clearall);
        delete = (Button) rootview.findViewById(R.id.delete);
        sort = (Button) rootview.findViewById(R.id.sort);
        moviedata = new MovieData();
        mRecycleView = (RecyclerView) rootview.findViewById(R.id.recycleview2);

        mRecycleViewAdapter = new MyRecycleViewAdapter(getContext(), moviedata.getMoviesList());

        mRecycleView.setAdapter(mRecycleViewAdapter);

        mRecycleView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(mLayoutManager);


        selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviedata.selectall();
                mRecycleViewAdapter.notifyDataSetChanged();
            }
        });

        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviedata.clearall();
                mRecycleViewAdapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class myObject {
                    int index;
                    HashMap<String, ?> mapobj;

                    public myObject(int x, HashMap<String, ?> y) {
                        index = x;
                        mapobj = y;
                    }
                }
                ArrayList<myObject> clean = new ArrayList<myObject>();
                for (int i = 0, j = 0; i < moviedata.getSize(); i++) {
                    HashMap<String, Boolean> temp =
                            (HashMap<String, Boolean>) moviedata.getMoviesList().get(i);
                    if (temp.get("selection") == true) {
                        myObject obj1 = new myObject(i, (HashMap<String, ?>) moviedata.getMoviesList().get(i));
                        clean.add(j, obj1);
                        j++;
                    }
                }
                Integer ci = clean.size();
                for (int i = 0; i < clean.size(); i++) {
                    int index = clean.get(i).index;
                    HashMap<String, Boolean> temp =
                            (HashMap<String, Boolean>) clean.get(i).mapobj;
                    moviedata.getMoviesList().remove(index - i);
                    mRecycleViewAdapter.notifyItemRemoved(index - i);
                }
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviedata.sortItemsByYear();
                mRecycleViewAdapter.notifyDataSetChanged();
            }
        });
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) moviedata.getItem(position);
                customOnClickRvListener.onRecycleViewItemClicked(view, movie);
            }

            public void onItemLongClick(View view, int position) {
                moviedata.addItem(position + 1, (HashMap) ((HashMap) moviedata.getItem(position)).clone());
                mRecycleViewAdapter.notifyItemInserted(position + 1);
            }

            @Override
            public void onItemSelected(View sharedImage, int position) {
                Fragment prevFrag = getFragmentManager().findFragmentById(R.id.task1framelayout);

                Fade exitFade = new Fade();
                exitFade.setDuration(300);
                prevFrag.setExitTransition(exitFade);
                HashMap<String, ?> movie = (HashMap<String, ?>) moviedata.getItem(position);
                MovieFragment details = MovieFragment.newInstance(movie);
                details.setSharedElementEnterTransition(new DetailsTransition());
                details.setEnterTransition(new Fade().setStartDelay(1000).setDuration(500));

                details.setExitTransition(exitFade);
                details.setSharedElementReturnTransition(new DetailsTransition());

                getFragmentManager().beginTransaction()
                        .addSharedElement(sharedImage, sharedImage.getTransitionName())
                        .replace(R.id.task1framelayout, details)
                        .addToBackStack(null)
                        .commit();
            }
        });

        itemAnimation();
        adapterAnimation();
        return rootview;
    }

    private void itemAnimation() {
        ScaleInAnimator anim = new ScaleInAnimator();
        anim.setAddDuration(5000);
        anim.setRemoveDuration(2000);
        mRecycleView.setItemAnimator(anim);
    }

    private void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecycleViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mRecycleView.setAdapter(scaleAdapter);
    }

    public interface CustomOnClickRecycleViewListener {
        public void onRecycleViewItemClicked(View v, HashMap<String, ?> movie);
    }
}
