package com.epicodus.mymovies.MovieListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.mymovies.Movie;
import com.epicodus.mymovies.R;
import com.epicodus.mymovies.RatingActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/7/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.posterThumb) ImageView mPosterThumb;
        @Bind(R.id.movieSynopsis) TextView mMovieSynopsis;
        @Bind(R.id.movieTitle) TextView mMovieTitle;

        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie) {
            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500" + movie.getPoster()).into(mPosterThumb);
            mMovieSynopsis.setText(movie.getSynopsis());
            mMovieTitle.setText(movie.getTitle());

        }
    }
}
