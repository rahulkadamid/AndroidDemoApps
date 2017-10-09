package com.example.rahul.assignment5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class myRecycleView extends Fragment {
    RecyclerView mRecycleView;
    MyRecycleViewAdapter mRecycleViewAdapter;
    MovieData moviedata;
    public myRecycleView() {
    }
    public class ActionBarCallBack implements ActionMode.Callback {
        int position;
        public ActionBarCallBack(int position)
        {
            this.position = position;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                 case R.id.contextual_or_pop_menu_delete:
                     moviedata.getMoviesList().remove(position);
                     mRecycleViewAdapter.notifyItemRemoved(position);
                     mode.finish();
                     break;
                 case R.id.contextual_or_pop_menu_copy:
                 moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
                 mRecycleViewAdapter.notifyItemInserted(position);
                 mode.finish();
                 break;
                 default:
                 break;
            }
            return false;
        }
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_or_popmenu, menu);
            return true;
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap)moviedata.getItem(position);
            mode.setTitle((String)hm.get("title"));
            return false;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public interface CustomOnClickRecycleViewListener {
        public void onRecycleViewItemClicked(View v, HashMap<String, ?> movie);
    }
    private CustomOnClickRecycleViewListener customOnClickRvListener;
    public static myRecycleView newInstance(int section_number) {
        myRecycleView mr = new myRecycleView();
        Bundle args = new Bundle();
        return mr;
    }

    public void sortMovieDataByYear()
    {
        moviedata.sortItemsByYear();
        mRecycleViewAdapter.notifyDataSetChanged();
    }

    public void sortMovieDataByName() {
        moviedata.sortItemsByName();
        mRecycleViewAdapter.notifyDataSetChanged();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();
        moviedata = new MovieData();
        mRecycleView = (RecyclerView)rootview.findViewById(R.id.recycleview2);
        mRecycleViewAdapter = new MyRecycleViewAdapter(getContext(), moviedata.getMoviesList());
        mRecycleView.setAdapter(mRecycleViewAdapter);
        mRecycleView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick (View view, int position) {
                HashMap<String,?> movie = (HashMap<String,?>) moviedata.getItem(position);
                customOnClickRvListener.onRecycleViewItemClicked(view,movie);
            }
            public void onItemLongClick ( View view , int position) {
                ActionBarCallBack abc = new ActionBarCallBack(position);
                AppCompatActivity abcd = (AppCompatActivity) getActivity();
                abcd.startSupportActionMode(abc);
            }
            public void onOverFlowMenuClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.contextual_or_pop_menu_copy:
                                moviedata.addItem(position+1, (HashMap) ((HashMap)moviedata.getItem(position)).clone());
                                mRecycleViewAdapter.notifyItemInserted(position);
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                moviedata.getMoviesList().remove(position);
                                mRecycleViewAdapter.notifyItemRemoved(position);
                                return true;
                         default:
                             return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_or_popmenu,popup.getMenu());
                popup.show();
            }
        });
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
          if (menu.findItem(R.id.recycle_fragment_menu) == null) {
            inflater.inflate(R.menu.recycle_fragment_toolbar, menu);
          }
        SearchView search = null;

         if (menu.findItem(R.id.recycle_fragment_actionview) == null) {
             inflater.inflate(R.menu.recycle_fragment_actionview, menu);
          }
          MenuItem menuItem = menu.findItem(R.id.recycle_fragment_searchitem);
          if (menuItem != null) {
           search = (SearchView)menuItem.getActionView();
          }
          if (search != null) {
              search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
              int position = moviedata.findFirst(query);
              if (position >= 0) {
                  mRecycleView.scrollToPosition(position+1);
              }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String query) {
              return true;
            }});
          }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.recycleview_toolbar_title) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle content) {
        super.onSaveInstanceState(content);
    }
}
