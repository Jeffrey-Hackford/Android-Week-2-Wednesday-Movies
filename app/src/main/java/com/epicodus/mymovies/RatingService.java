package com.epicodus.mymovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 7/7/16.
 */
public class RatingService {

 public static void findByRating(String rating, Callback callback){
     OkHttpClient client = new OkHttpClient.Builder().build();

     HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DISCOVER_BASE_URL).newBuilder();
     urlBuilder.addQueryParameter("certification_country", "US");
     urlBuilder.addQueryParameter("certification", rating);
     urlBuilder.addQueryParameter("sort_by", "popularity.desc");
     urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.API_KEY);
     String url = urlBuilder.build().toString();

     Request request= new Request.Builder()
             .url(url)
             .build();

     Call call = client.newCall(request);
     call.enqueue(callback);
 }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieResultsJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = movieResultsJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject movieJSON = resultsJSON.getJSONObject(i);
                    String title = movieJSON.getString("original_title");
                    String poster = movieJSON.getString("poster_path");
                    String synopsis = movieJSON.getString("overview");

                    Movie movie = new Movie(title, poster, synopsis);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
