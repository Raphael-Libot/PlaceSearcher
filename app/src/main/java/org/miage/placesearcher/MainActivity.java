package org.miage.placesearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.textView)
    public void decreaseRating(){
        float actualRating = this.ratingBar.getRating();
        if (actualRating > 0) {
            this.ratingBar.setRating(actualRating - 1);
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        float actualRating = this.ratingBar.getRating();
        if (actualRating < this.ratingBar.getMax()) {
            this.ratingBar.setRating(actualRating + 1);
        }
    }
}
