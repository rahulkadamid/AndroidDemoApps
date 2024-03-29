package com.example.rahul.assignment9;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    static OnItemClickListener mItemClickListener;
    private List<Map<String, ?>> mDataset;
    private int lastPosition = -1;
    private Context mcontext;

    public MyRecycleViewAdapter(Context myContext, List<Map<String, ?>> myDataset) {
        mDataset = myDataset;
        mcontext = myContext;
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
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);

        public void onOverFlowMenuClick(View v, final int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView title;
        public TextView description;
        public CheckBox checkBox;
        public ImageView overflow_image;

        public ViewHolder(View v) {
            super(v);
            imageview = (ImageView) v.findViewById(R.id.cardview_imageview);
            title = (TextView) v.findViewById(R.id.cardview_title);
            description = (TextView) v.findViewById(R.id.cardview_description);
            checkBox = (CheckBox) v.findViewById(R.id.cardview_checkBox);
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
            title.setText((String) movie.get("name"));
            imageview.setImageResource((Integer) movie.get("image"));
            description.setText((String) movie.get("description"));
            checkBox.setChecked((Boolean) movie.get("selection"));
            checkBox.setOnClickListener(new View.OnClickListener() {
                final HashMap<String, Boolean> temp = (HashMap<String, Boolean>) movie;

                @Override
                public void onClick(View v) {
                    temp.put("selection", true);
                }
            });
        }

    }
}
