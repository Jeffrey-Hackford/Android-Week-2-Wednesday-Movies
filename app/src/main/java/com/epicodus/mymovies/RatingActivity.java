package com.epicodus.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.epicodus.mymovies.adapters.MovieListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Guest on 7/7/16.
 */
public class RatingActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;

    public ArrayList<Movie> moviesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String rating = intent.getStringExtra("rating");
        getMovieList(rating);


    }

    private void getMovieList(String rating){
        final RatingService ratingService = new RatingService();
        ratingService.findByRating(rating, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                String jsonData = response.body().toString();



                moviesList = ratingService.processResults(response);


                RatingActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] movieNames = new String[moviesList.size()];
                        for (int i = 0; i < movieNames.length; i++) {
                            movieNames[i] = moviesList.get(i).getTitle();
                        }
                        Log.v("movies", "test");
                        mAdapter = new MovieListAdapter(getApplicationContext(), moviesList);
                        mRecyclerView.setAdapter(mAdapter);
//                        Log.d("testing", getApplicationContext().toString());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RatingActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
