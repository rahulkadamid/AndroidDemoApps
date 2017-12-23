package com.example.rahul.assignment9;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieData {
    List<Map<String, ?>> moviesList;
    DatabaseReference mRef;
    MyFirebaseRecylerAdapter myFirebaseRecylerAdapter;
    Context mContext;

    public MovieData() {
        moviesList = new ArrayList<Map<String, ?>>();
        mRef = FirebaseDatabase.getInstance().getReference().child("moviedata").getRef();
        myFirebaseRecylerAdapter = null;
        mContext = null;
    }

    public void setAdapter(MyFirebaseRecylerAdapter mAdapter) {
        myFirebaseRecylerAdapter = mAdapter;
    }

    public void removeItemFromServer(Map<String, ?> movie) {
        if (movie != null) {
            String id = (String) movie.get("id");
            mRef.child(id).removeValue();
        }
    }

    public void addItemToServer(Map<String, ?> movie) {
        if (movie != null) {
            String id = (String) movie.get("id");
            mRef.child(id).setValue(movie);
        }
    }

    public DatabaseReference getFireBaseRef() {
        return mRef;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public int getSize() {
        return moviesList.size();
    }

    public HashMap getItem(int i) {
        if (i >= 0 && i < moviesList.size()) {
            return (HashMap) moviesList.get(i);
        } else return null;
    }

    public void onItemRemovedFromCloud(HashMap item) {
        int position = -1;
        String id = (String) item.get("id");
        for (int i = 0; i < moviesList.size(); i++) {
            HashMap movie = (HashMap) moviesList.get(i);
            String mid = (String) movie.get("id");
            if (mid.equals(id)) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            moviesList.remove(position);
            Toast.makeText(mContext, "Item Removed:" + id, Toast.LENGTH_SHORT).show();

        }
    }

    public void onItemAddedToCloud(HashMap item) {
        int insertPosition = 0;
        String id = (String) item.get("id");
        for (int i = 0; i < moviesList.size(); i++) {
            HashMap movie = (HashMap) moviesList.get(i);
            String mid = (String) movie.get("id");
            if (mid.equals(id)) {
                return;
            }
            if (mid.compareTo(id) < 0) {
                insertPosition = i + 1;
            } else {
                break;
            }
        }
        moviesList.add(insertPosition, item);
    }

    public void onItemUpdatedToCloud(HashMap item) {
        String id = (String) item.get("id");
        for (int i = 0; i < moviesList.size(); i++) {
            HashMap movie = (HashMap) moviesList.get(i);
            String mid = (String) movie.get("id");
            if (mid.equals(id)) {
                moviesList.remove(i);
                moviesList.add(i, item);
                break;
            }
        }
    }

    public void initializeDataFromCloud() {
        moviesList.clear();
        mRef.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                HashMap<String, String> movie = (HashMap<String, String>) dataSnapshot.getValue();
                onItemAddedToCloud(movie);
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                HashMap<String, String> movie = (HashMap<String, String>) dataSnapshot.getValue();
                onItemUpdatedToCloud(movie);
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
                HashMap<String, String> movie = (HashMap<String, String>) dataSnapshot.getValue();
                onItemRemovedFromCloud(movie);
            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public int findFirst(String query) {

        for (int i = 0; i < moviesList.size(); i++) {
            HashMap hm = (HashMap) getMoviesList().get(i);
            if (((String) hm.get("name")).toLowerCase().contains(query.toLowerCase())) {
                return i;
            }
        }
        return 0;

    }
}
