package org.miage.placesearcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Simon on 15/01/2018.
 */

public class PlaceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.street_name)
    TextView streetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_place_details);
        ButterKnife.bind(this);

        String intentData = getIntent().getStringExtra("streetName");
        streetName.setText(intentData);
    }
}
