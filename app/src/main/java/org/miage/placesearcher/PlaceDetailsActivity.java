package org.miage.placesearcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Simon on 15/01/2018.
 */

public class PlaceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.street_name)
    TextView streetName;

    private String streetNameToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_place_details);
        ButterKnife.bind(this);
        streetNameToSend = getIntent().getStringExtra("streetName");
        streetName.setText(streetNameToSend);
    }

    @OnClick(R.id.share_button)
    public void onShareClick() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String toSend = "Hey koukou, bienvenue dans la rue : " + streetNameToSend;
        sendIntent.putExtra(Intent.EXTRA_TEXT, toSend);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @OnClick(R.id.search_button)
    public void onSearchClick() {
        String url = "https://www.google.fr/search?q=" + streetNameToSend;
        Uri uri = Uri.parse(url);
        Intent searchIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(searchIntent);
    }
}
