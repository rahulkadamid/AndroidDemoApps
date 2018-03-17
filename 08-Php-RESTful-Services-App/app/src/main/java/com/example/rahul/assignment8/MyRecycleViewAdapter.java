package com.example.rahul.assignment8;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    static OnItemClickListener mItemClickListener;
    LruCache<String, Bitmap> mImageMemoryCache;
    private List<Map<String, ?>> mDataset;
    private Context mcontext;

    public MyRecycleViewAdapter(Context myContext, List<Map<String, ?>> myDataset) {
        mDataset = myDataset;
        mcontext = myContext;
        if (mImageMemoryCache == null) {
            final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
            final int cacheSize = maxMemory / 8;
            mImageMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String myUrl, Bitmap bitMap) {
                    return bitMap.getByteCount() / 1024;
                }
            };
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= 4) {
            return 1;
        } else if (position >= getItemCount() - 5) {
            return 3;
        } else
            return 2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> movie = mDataset.get(position);
        holder.bindMovieData(movie);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

        void onOverFlowMenuClick(View v, final int position);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView title;
        public TextView description;
        public ImageView overflow_image;

        public ViewHolder(View v) {
            super(v);
            imageview = (ImageView) v.findViewById(R.id.cardview_imageview);
            title = (TextView) v.findViewById(R.id.cardview_title);
            description = (TextView) v.findViewById(R.id.cardview_description);
            overflow_image = (ImageView) v.findViewById(R.id.cardview_overflow);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            mItemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            mItemClickListener.onItemLongClick(v, getAdapterPosition());
                        }
                    }
                    return true;
                }
            });
            overflow_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onOverFlowMenuClick(v, getAdapterPosition());
                }
            });
        }

        public void bindMovieData(final Map<String, ?> movie) {
            title.setText((String) movie.get("Title"));
            imageview.setImageDrawable(null);
            String newurl = "http://image.tmdb.org/t/p/w185/" + movie.get("Poster");
            final Bitmap bitmap = mImageMemoryCache.get(newurl);
            if (bitmap != null) {
                imageview.setImageBitmap(bitmap);
            } else {
                MyDownLoadImageAsyncTask myDownLoadImageAsyncTask = new
                        MyDownLoadImageAsyncTask(imageview);
                myDownLoadImageAsyncTask.execute(newurl);
            }

            description.setText((String) movie.get("Overview"));
        }

    }
}
