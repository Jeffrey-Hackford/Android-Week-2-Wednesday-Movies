package com.epicodus.mymovies;

/**
 * Created by Guest on 7/6/16.
 */
public class Movie {
    private String mTitle;
    private String mPoster;
    private String mSynopsis;

    public Movie() {}

    public Movie(String title, String poster, String synopsis){
        this.mTitle = title;
        this.mPoster = poster;
        this.mSynopsis = synopsis;
    }

    public String getTitle() { return mTitle; }

    public String getPoster() { return mPoster; }

    public String getSynopsis() { return mSynopsis; }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public void setSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

}
