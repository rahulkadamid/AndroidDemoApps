package com.example.rahul.assignment8;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MovieFragment extends Fragment {

    private static final String ARG_MOVIE = "Movie";
    private HashMap<String, ?> movie;
    private int total = 0;
    TextView Title;
    TextView Description;
    ImageView image;
    TextView YearInfo;
    TextView VotingCount;
    TextView Rating;

    public static MovieFragment newInstance(HashMap<String, ?> movie) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
        {
            movie = (HashMap<String, ?> )getArguments().getSerializable(ARG_MOVIE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.moviefragment, container, false);
        Title = (TextView) rootView.findViewById(R.id.movietitle);
        Description = (TextView) rootView.findViewById(R.id.moviedescription);
        image = (ImageView) rootView.findViewById(R.id.movieimageView);
        YearInfo = (TextView) rootView.findViewById(R.id.movieyear);
        VotingCount = (TextView) rootView.findViewById(R.id.votingcount);
        Rating = (TextView) rootView.findViewById(R.id.rating);

        if (movie == null)
        {
            return rootView;
        }
        if (movie.get("Title") != null) {
            Title.setText(movie.get("Title").toString());
        }
        if (movie.get("Overview") != null) {
            Description.setText(movie.get("Overview").toString());
        }
        MyDownLoadImageAsyncTask myDownLoadImageAsyncTask = new MyDownLoadImageAsyncTask(image);
        String newurl = "http://image.tmdb.org/t/p/w185/" + movie.get("Poster");
        myDownLoadImageAsyncTask.execute(newurl);
        if (movie.get("ReleaseDay") != null) {
            YearInfo.setText("ReleaseDay: " + movie.get("ReleaseDay").toString());
        }
        if (movie.get("VoteCnt") != null) {
            VotingCount.setText("Voting Count: " + movie.get("VoteCnt").toString());
        }
        if (movie.get("VoteAvg") != null) {
            Rating.setText("Rating: " + movie.get("VoteAvg").toString());
        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class MyDownLoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;

        public MyDownLoadImageAsyncTask(ImageView imageView) {
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            for (String url : urls) {
                bitmap = MyUtility.downloadImageusingHTTPGetRequest(url);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if ((imageViewWeakReference != null) && (bitmap != null)) {
                final ImageView imageView = imageViewWeakReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
