package com.example.rahul.assignment9;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.lang.ref.WeakReference;

public class MyFirebaseRecylerAdapter extends FirebaseRecyclerAdapter<Movie, MyFirebaseRecylerAdapter.MovieViewHolder> {

    static RecycleItemClickListener onItemClickListener;
    static LruCache<String, Bitmap> mImageMemoryCache;
    private Context mContext;

    public MyFirebaseRecylerAdapter(Class<Movie> modelClass, int modelLayout,
                                    Class<MovieViewHolder> holder, DatabaseReference ref,
                                    Context context) {
        super(modelClass, modelLayout, holder, ref);
        this.mContext = context;
        if (mImageMemoryCache == null) {
            /* in KiloBytes */
            final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
            // Use 1/8 of available memory for this cache
            final int cacheSize = maxMemory / 8;
            mImageMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String myUrl, Bitmap bitMap) {
                    // The cache size will be measured in KiloBytes rather than number of items
                    return bitMap.getByteCount() / 1024;
                }
            };
        }

    }

    public void setOnItemClickListener(final RecycleItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }

    @Override
    protected void populateViewHolder(MovieViewHolder movieViewHolder, Movie movie, int i) {
        movieViewHolder.bindMovieData(movie);
    }

    public interface RecycleItemClickListener {
         void onItemClick(View view, int position);
         void onItemLongClick(View view, int position);
         void onOverFlowMenuClick(View v, final int position);
    }

    private static class MyDownLoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;

        public MyDownLoadImageAsyncTask(ImageView imageView) {
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            for (String url : urls) {
                bitmap = MyUtility.downloadImageusingHTTPGetRequest(url);
                if (bitmap != null) {
                    mImageMemoryCache.put(url, bitmap);
                }
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

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView title;
        public TextView description;
        public CheckBox checkBox;
        public ImageView overflow_image;

        public MovieViewHolder(View v) {
            super(v);
            imageview = (ImageView) v.findViewById(R.id.cardview_imageview);
            title = (TextView) v.findViewById(R.id.cardview_title);
            description = (TextView) v.findViewById(R.id.cardview_description);
            checkBox = (CheckBox) v.findViewById(R.id.cardview_checkBox);
            overflow_image = (ImageView) v.findViewById(R.id.cardview_overflow);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }
            });
            overflow_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onOverFlowMenuClick(v, getAdapterPosition());
                }
            });
        }

        public void bindMovieData(final Movie movie) {
            title.setText((String) movie.getName());
            description.setText((String) movie.getDescription());
            checkBox.setChecked(false);

            checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });
            imageview.setImageDrawable(null);
            final Bitmap bitmap = mImageMemoryCache.get((String) movie.getUrl());
            if (bitmap != null) {
                imageview.setImageBitmap(bitmap);
            } else {
                MyDownLoadImageAsyncTask myDownLoadImageAsyncTask = new
                        MyDownLoadImageAsyncTask(imageview);
                myDownLoadImageAsyncTask.execute((String) movie.getUrl());
            }
        }
    }
}
