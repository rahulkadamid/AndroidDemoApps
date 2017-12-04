package com.example.rahul.assignment2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;

import java.util.HashMap;

public class Task5Activity extends AppCompatActivity {

    MovieData movieData = null;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    RatingBar ratebar;
    ImageView imageViewMovie;
    SeekBar seekBar;
    int i = 0;
    private GestureDetectorCompat detector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void setImageView(int i) {
        movieData = new MovieData();
        final HashMap movie2 = movieData.getItem(i);
        int imageId = (Integer) movie2.get("image");
        imageViewMovie.setImageResource(imageId);
    }

    public void setTextView(int i) {
        movieData = new MovieData();
        final HashMap movie2 = movieData.getItem(i);
        String title = (String) movie2.get("title");
        textView.setText(title);
        String overview = (String) movie2.get("overview");
        textView2.setText(overview);
        String language = (String) movie2.get("language");
        textView4.setText("Language: " + language);
        String release = (String) movie2.get("release");
        textView3.setText("Release Date: " + release);
        movie2.get("release");
        double r = (Double) movie2.get("voteAverage");
        float f = (float) r / 2.0f;
        ratebar.setRating(f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);
        textView = (TextView) findViewById(R.id.textView5);
        textView2 = (TextView) findViewById(R.id.textView6);
        textView4 = (TextView) findViewById(R.id.textView6b);
        textView3 = (TextView) findViewById(R.id.textView7);
        ratebar = (RatingBar) findViewById(R.id.ratingBar3);
        imageViewMovie = (ImageView) findViewById(R.id.imageViewMovie);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        setImageView(0);
        setTextView(0);

        imageViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Task5Activity.this, "Toast message.", Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(R.id.imageViewMovie), "Snackbar message.", Snackbar.LENGTH_LONG).show();
            }
        });

        imageViewMovie.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view1) {
                seekBar.setProgress(50);
                return true;
            }

        });

        seekBar.setProgress(50);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ViewGroup.LayoutParams dim = imageViewMovie.getLayoutParams();
                dim.width = progress * 8;
                dim.height = progress * 8;
                imageViewMovie.setLayoutParams(dim);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        detector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

                float diffX = event2.getX() - event1.getX();
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {


                    if (diffX > 0) {
                        if (i > 0) {
                            i = i - 1;
                            setImageView(i);
                            setTextView(i);
                        }
                    } else {
                        if (i < 24) {
                            i = i + 1;
                            setImageView(i);
                            setTextView(i);
                        }

                    }
                }
                return true;
            }
        });


    }


}