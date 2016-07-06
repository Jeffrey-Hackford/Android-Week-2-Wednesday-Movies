package com.epicodus.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultActivity extends AppCompatActivity {

    public Movie mMovie = new Movie();
    @Bind(R.id.movieTitleTextView) TextView mMovieTitle;
    @Bind(R.id.posterImageView)
    ImageView mPosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("title") != null) {
            String title = intent.getStringExtra("title");
            Log.d("title", title);
            getMovie(title);
        } else if (intent.getStringExtra("releaseDate") != null) {
            String releaseDate = intent.getStringExtra("releaseDate");
            Log.d("release date", releaseDate);
            getMovie(releaseDate);
        } else if (intent.getStringExtra("rating") != null) {
            String rating = intent.getStringExtra("rating");
            Log.d("rating", rating);
            getMovie(rating);
        }
    }

    public void getMovie(String title){
        final MovieService movieService = new MovieService();
        movieService.findMovies(title, new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {e.printStackTrace();}

            @Override
            public void onResponse(Call call, Response response){
                mMovie = movieService.processResults(response);

                ResultActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("photo url", "http://image.tmdb.org/t/p/w500" + mMovie.getPoster());
                        Picasso.with(ResultActivity.this)
                                .load("http://image.tmdb.org/t/p/w500" + mMovie.getPoster()).into(mPosterImageView);

                        mMovieTitle.setText(mMovie.getSynopsis());
                    }
                });
            }
        });
    }
}
