package org.miage.placesearcher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Simon on 15/01/2018.
 */

public class PlaceDetailsActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 1234;

    @BindView(R.id.street_name)
    TextView streetName;

    @BindView(R.id.image)
    ImageView image;

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

    @OnClick(R.id.search_image)
    public void onSearchImageClick() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECT_PHOTO == requestCode) {
            try {
                Uri selectedImage = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
