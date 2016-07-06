package com.epicodus.mymovies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.mainTitleTextView) TextView mMainTitleTextView;
    @Bind(R.id.titleEditText) EditText mTitleEditText;
    @Bind(R.id.ratingEditText) EditText mRatingEditText;
    @Bind(R.id.releaseDateEditText) EditText mReleaseDateEditText;
    @Bind(R.id.titleSearchButton) Button mTitleSearchButton;
    @Bind(R.id.ratingSearchButton) Button mRatingSearchButton;
    @Bind(R.id.releaseDateSearchButton) Button mReleaseDateSearchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ritaglio = Typeface.createFromAsset(getAssets(), "fonts/Ritaglio.ttf");
        mMainTitleTextView.setTypeface(ritaglio);

        mTitleSearchButton.setOnClickListener(this);
        mReleaseDateSearchButton.setOnClickListener(this);
        mRatingSearchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v == mTitleSearchButton){

            String title = mTitleEditText.getText().toString();
            Intent titleIntent = new Intent(MainActivity.this, ResultActivity.class);
            titleIntent.putExtra("title", title);
            startActivity(titleIntent);

        } else if (v == mReleaseDateSearchButton){

            String releaseDate = mReleaseDateEditText.getText().toString();
            Intent releaseDateIntent = new Intent(MainActivity.this, ResultActivity.class);
            releaseDateIntent.putExtra("releaseDate", releaseDate);
            startActivity(releaseDateIntent);

        } else if (v == mRatingSearchButton){

            String rating = mRatingEditText.getText().toString();
            Intent ratingIntent = new Intent(MainActivity.this, ResultActivity.class);
            ratingIntent.putExtra("rating", rating);
            startActivity(ratingIntent);

        }
    }
}
