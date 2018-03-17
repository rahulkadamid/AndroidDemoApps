package com.example.rahul.assignment9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MyRecycleViewFragment extends Fragment {
    RecyclerView mRecycleView;
    MyFirebaseRecylerAdapter myFirebaseRecylerAdapter;
    MovieData moviedata;
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
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();
        moviedata = new MovieData();

        mRecycleView = (RecyclerView) rootview.findViewById(R.id.recycleview2);

        DatabaseReference childref = FirebaseDatabase.getInstance().getReference()
                .child("moviedata").getRef();

        myFirebaseRecylerAdapter = new MyFirebaseRecylerAdapter(Movie.class, R.layout.listrow,
                MyFirebaseRecylerAdapter.MovieViewHolder.class, childref, getContext());

        if (moviedata.getSize() == 0) {
            moviedata.setAdapter(myFirebaseRecylerAdapter);
            moviedata.setContext(getActivity());
            moviedata.initializeDataFromCloud();
        }
        mRecycleView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        mRecycleView.setLayoutManager(mLayoutManager);
        myFirebaseRecylerAdapter.setOnItemClickListener(new MyFirebaseRecylerAdapter.RecycleItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {

                HashMap<String, ?> movie = (HashMap<String, ?>) moviedata.getItem(position);
                String id = (String) movie.get("id");
                DatabaseReference ref = moviedata.getFireBaseRef();
                ref.child(id).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                        HashMap<String, String> movie = (HashMap<String, String>) dataSnapshot.getValue();
                        customOnClickRvListener.onRecycleViewItemClicked(view, movie);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }

            @Override
            public void onOverFlowMenuClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        HashMap movie;
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.contextual_or_pop_menu_copy:
                                movie = (HashMap) ((HashMap) moviedata.getItem(position).clone());
                                movie.put("id", ((String) movie.get("id") + "_new"));
                                moviedata.addItemToServer(movie);
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                movie = (HashMap) ((HashMap) moviedata.getItem(position));
                                moviedata.removeItemFromServer(movie);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_or_popmenu, popup.getMenu());
                popup.show();
            }
        });
        mRecycleView.setAdapter(myFirebaseRecylerAdapter);
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
            search = (SearchView) menuItem.getActionView();
        }
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    int position = moviedata.findFirst(query);
                    if (position >= 0) {
                        mRecycleView.scrollToPosition(position);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.recycleview_toolbar_title) {
            Toast.makeText(getContext(), "Inside recycle fragment title click", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle content) {
        super.onSaveInstanceState(content);
    }

    public interface CustomOnClickRecycleViewListener {
        void onRecycleViewItemClicked(View v, HashMap<String, ?> movie);
    }

    public class ActionBarCallBack implements ActionMode.Callback {
        int position;

        public ActionBarCallBack(int position) {
            this.position = position;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.contextual_or_pop_menu_delete:
                    moviedata.getMoviesList().remove(position);
                    mode.finish();
                    break;
                case R.id.contextual_or_pop_menu_copy:
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
            HashMap hm = (HashMap) moviedata.getItem(position);
            mode.setTitle((String) hm.get("name"));
            return false;
        }
    }
}
