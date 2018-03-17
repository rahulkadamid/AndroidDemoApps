package com.example.rahul.assignment6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
   // private List<Map<String,?>> mDataset;
    private int lastPosition = -1;
    private Context mcontext;
    DatabaseHelper myDB;
    List<MyMovie> mItems;
    int vType;

    static  OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
        public void onOverFlowMenuClick(View v, final int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    /*
    public MyRecycleViewAdapter(Context myContext, List<Map<String,?>> myDataset)
    {
        mDataset = myDataset;
        mcontext = myContext;
    }
    */
    public MyRecycleViewAdapter( DatabaseHelper db , Context context ) {

        myDB = db;
        mItems = myDB.getAllMovies();
        mcontext = context ;
    }

    public MyRecycleViewAdapter( DatabaseHelper db , List<MyMovie> imItems , Context context ) {

        myDB = db;
        mItems = imItems;
        mcontext = context ;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
        public ImageView overflow_image;
        public ViewHolder(View v) {
            super(v);
            imageview = (ImageView)v.findViewById(R.id.cardview_imageview);
            title = (TextView)v.findViewById(R.id.cardview_title);
            description = (TextView)v.findViewById(R.id.cardview_description);
            checkBox = (CheckBox)v.findViewById(R.id.cardview_checkBox);
            overflow_image = (ImageView)v.findViewById(R.id.cardview_overflow);

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
            overflow_image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onOverFlowMenuClick(v, getAdapterPosition());
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
        //Map<String, ?> movie = mDataset.get(position);
        //holder.bindMovieData(movie);

        MyMovie movie = mItems.get( position );
        String url = "http://image.tmdb.org/t/p/w185/";
        Picasso.with( mcontext ).load( url + movie.getPoster()).into( holder.imageview );
        holder.title.setText(( String ) movie.getTitle ());
        String str = (( String ) movie.getOverview ());
        //int maxL = (str . length () < 200) ?str. length () :200;
        //str = str.substring (0, maxL - 1) + " ... ";
        holder.description.setText( str );
        //if( holder.checkBox != null )
        //  holder.checkBox.setChecked(( boolean )( mItems.get( position ).isChecked()));

        //setAnimation( holder.imageview , position );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
       v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
}
