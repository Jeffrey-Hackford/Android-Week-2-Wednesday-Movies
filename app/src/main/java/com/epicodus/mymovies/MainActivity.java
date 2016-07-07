package com.epicodus.mymovies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.mainTitleTextView) TextView mMainTitleTextView;
    @Bind(R.id.titleEditText) EditText mTitleEditText;
    @Bind(R.id.titleSearchButton) Button mTitleSearchButton;
    @Bind(R.id.ratingSearchButton) Button mRatingSearchButton;
    @Bind(R.id.releaseDateSearchButton) Button mReleaseDateSearchButton;

    String setRating;


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

        } else if (v == mRatingSearchButton){
            Intent ratingIntent = new Intent(MainActivity.this, RatingActivity.class);
            ratingIntent.putExtra("rating", setRating);
            startActivity(ratingIntent);
        }
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.rRadio:
                if (checked)
                   setRating = "R";
                break;
            case R.id.pg13Radio:
                if (checked)
                    setRating = "PG-13";
                break;
            case R.id.pgRadio:
                if (checked)
                    setRating = "PG";
                break;
            case R.id.gRadio:
                if (checked)
                    setRating = "G";
                break;
        }
    }
}
