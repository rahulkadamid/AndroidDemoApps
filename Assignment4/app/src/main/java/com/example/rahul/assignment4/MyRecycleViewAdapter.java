package com.example.rahul.assignment4;

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


public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder>
{
    private List<Map<String,?>> mDataset;
    private int lastPosition = -1;
    private Context mcontext;

    static  OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick ( View view , int position );
        public void onItemLongClick ( View view , int position );
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MyRecycleViewAdapter(Context myContext, List<Map<String,?>> myDataset)
    {
        mDataset = myDataset;
        mcontext = myContext;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position <= 4)
        {
            return 1;
        }
        else if (position >= getItemCount()-5)
        {
            return 3;
        }
        else
            return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageview;
        public TextView title;
        public TextView description;
        public CheckBox checkBox;
        public ViewHolder(View v) {
            super(v);
            imageview = (ImageView)v.findViewById(R.id.imageView3);
            title = (TextView)v.findViewById(R.id.textView5);
            description = (TextView)v.findViewById(R.id.textView6);
            checkBox = (CheckBox)v.findViewById(R.id.checkBox);

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

            v.setOnLongClickListener(new View.OnLongClickListener(){
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
        }

        public void bindMovieData(final Map<String, ?> movie) {
            title.setText((String)movie.get("title"));
            imageview.setImageResource((Integer)movie.get("image"));
            description.setText((String)movie.get("overview"));
            checkBox.setChecked((Boolean)movie.get("selection"));
            checkBox.setOnClickListener(new View.OnClickListener(){
                final HashMap<String, Boolean> temp = (HashMap<String,Boolean>)movie;
                @Override
                public void onClick(View v) {
                      temp.put("selection", true);
                }
            });
        }

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Map<String, ?> movie = mDataset.get(position);
        holder.bindMovieData(movie);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;

        switch (viewType) {
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow2, parent, false);
                break;
            case 3:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow3, parent, false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
                break;
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
}
