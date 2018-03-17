package com.example.rahul.assignment8;

import android.os.AsyncTask;
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

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MyRecycleViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    RecyclerView mRecycleView;
    MyRecycleViewAdapter mRecycleViewAdapter;
    MovieData moviedata;
    MovieDataJson threadMovieData;
    private CustomOnClickRecycleViewListener customOnClickRvListener;

    public MyRecycleViewFragment() {
    }

    public static MyRecycleViewFragment newInstance(int section_number) {
        MyRecycleViewFragment mr = new MyRecycleViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        mr.setArguments(args);
        return mr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        moviedata = new MovieData();
        threadMovieData = new MovieDataJson();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootview = inflater.inflate(R.layout.recycleviewfragment, container, false);
        customOnClickRvListener = (CustomOnClickRecycleViewListener) rootview.getContext();

        mRecycleView = (RecyclerView) rootview.findViewById(R.id.recycleview2);
        mRecycleViewAdapter = new MyRecycleViewAdapter(getContext(), moviedata.getMoviesList());
        mRecycleView.setAdapter(mRecycleViewAdapter);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) moviedata.getItem(position);
                String id = (String) movie.get("MovieId");
                String url = MovieDataJson.PHP_SERVER + "movies/id/" + id;
                if (url != null) {
                    MyDownLoadMovieDetailAsyncTask downloadDetail = new
                            MyDownLoadMovieDetailAsyncTask(customOnClickRvListener);
                    downloadDetail.execute(url);
                }
            }

            public void onItemLongClick(View view, int position) {
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
                                HashMap<String, ?> oldmovie = (HashMap) moviedata.getItem(position);
                                HashMap newmovie = new HashMap();
                                newmovie.put("MovieId", oldmovie.get("MovieId"));
                                newmovie.put("Title", oldmovie.get("Title") + "_new");
                                newmovie.put("VoteCnt", oldmovie.get("VoteCnt"));
                                newmovie.put("VoteAvg", oldmovie.get("VoteAvg"));
                                newmovie.put("Popularity", oldmovie.get("Popularity"));
                                newmovie.put("Poster", oldmovie.get("Poster"));
                                newmovie.put("Backdrop", oldmovie.get("Backdrop"));
                                newmovie.put("Overview", oldmovie.get("Overview"));
                                newmovie.put("ReleaseDay", oldmovie.get("ReleaseDay"));
                                newmovie.put("selection", false);
                                threadMovieData.addItem(position, newmovie);
                                moviedata.addItem(position, newmovie);
                                mRecycleViewAdapter.notifyItemInserted(position);
                                return true;
                            case R.id.contextual_or_pop_menu_delete:
                                HashMap deletemovie = moviedata.getItem(position);
                                threadMovieData.deleteItem(position, deletemovie);
                                moviedata.getMoviesList().remove(position);
                                mRecycleViewAdapter.notifyItemRemoved(position);
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
        MyDownLoadJsonAsyncTask downLoadJsonAsyncTask = new
                MyDownLoadJsonAsyncTask(mRecycleViewAdapter);
        String url = MovieDataJson.PHP_SERVER + "movies/";
        downLoadJsonAsyncTask.execute(url);
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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
                    String url = MovieDataJson.PHP_SERVER + "movies/rating/" + query;
                    if (url != null) {
                        MyDownLoadJsonAsyncTask downLoadJsonAsyncTask = new
                                MyDownLoadJsonAsyncTask(mRecycleViewAdapter);
                        downLoadJsonAsyncTask.execute(url);
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
        return super.onOptionsItemSelected(item);
    }

    public interface CustomOnClickRecycleViewListener {
        void onRecycleViewItemClicked(HashMap<String, ?> movie);
    }

    private class MyDownLoadJsonAsyncTask extends AsyncTask<String, Void, MovieDataJson> {
        private final WeakReference<MyRecycleViewAdapter> adapterWeakReference;

        public MyDownLoadJsonAsyncTask(MyRecycleViewAdapter myRecycleViewAdapter) {
            adapterWeakReference = new WeakReference<MyRecycleViewAdapter>(myRecycleViewAdapter);
        }

        @Override
        protected MovieDataJson doInBackground(String... urls) {
            threadMovieData.moviesList.clear();
            for (String url : urls) {
                threadMovieData.downloadMovieDataJson(url);
            }
            return threadMovieData;
        }

        @Override
        protected void onPostExecute(MovieDataJson threadMovieData) {
            moviedata.moviesList.clear();
            for (int i = 0; i < threadMovieData.getSize(); i++) {
                moviedata.addItem(i, threadMovieData.getItem(i));
            }
            if (adapterWeakReference != null) {
                final MyRecycleViewAdapter myRecycleViewAdapter = adapterWeakReference.get();
                if (myRecycleViewAdapter != null)
                    myRecycleViewAdapter.notifyDataSetChanged();
            }
        }
    }

    public class ActionBarCallBack implements ActionMode.Callback {
        int position;

        public ActionBarCallBack(int position) {
            this.position = position;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap) moviedata.getItem(position);
            mode.setTitle((String) hm.get("Title"));
            return false;
        }
    }

    private class MyDownLoadMovieDetailAsyncTask extends AsyncTask<String, Void, HashMap> {
        private final WeakReference<CustomOnClickRecycleViewListener> weakCustomOnClickReference;

        public MyDownLoadMovieDetailAsyncTask(CustomOnClickRecycleViewListener customOnClickRecycleViewListener) {
            weakCustomOnClickReference = new
                    WeakReference<CustomOnClickRecycleViewListener>(customOnClickRecycleViewListener);
        }

        @Override
        protected HashMap doInBackground(String... urls) {
            MovieDataJson threadMovieData2 = new MovieDataJson();
            for (String url : urls) {
                threadMovieData2.downloadMovieDataJson(url);
            }
            return threadMovieData2.getItem(0);
        }

        @Override
        protected void onPostExecute(HashMap movie) {

            if (weakCustomOnClickReference != null) {
                final CustomOnClickRecycleViewListener customOnClickRecycleViewListener =
                        weakCustomOnClickReference.get();

                if (customOnClickRecycleViewListener != null) {
                    customOnClickRecycleViewListener.onRecycleViewItemClicked(movie);
                }
            }
        }
    }
}
