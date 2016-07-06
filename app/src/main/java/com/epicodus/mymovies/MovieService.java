package com.epicodus.mymovies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {
    public static void findMovies(String title, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMATER, title);
        urlBuilder.addQueryParameter(Constants.API_KEY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public Movie processResults(Response response){
        Movie finalMovie = new Movie();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray movieArray = movieJSON.getJSONArray("results");
                JSONObject movieResult = movieArray.getJSONObject(0);
                String title = movieResult.getString("original_title");
                String synopsis = movieResult.getString("overview");
                String poster = movieResult.getString("poster_path");
                finalMovie.setTitle(title);
                finalMovie.setPoster(poster);
                finalMovie.setSynopsis(synopsis);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return finalMovie;

    }
}
