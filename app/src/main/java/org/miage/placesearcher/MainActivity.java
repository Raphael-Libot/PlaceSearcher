package org.miage.placesearcher;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.miage.placesearcher.model.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String GET_URL = "https://api-adresse.data.gouv.fr/search/?q=Place%20du%20commerce";
    private static final String TAG = MainActivity.class.toString();

    @BindView(R.id.items)
    ListView itemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createList();
    }

    private void createList(){
        List<Place> listItems = new ArrayList<Place>();
        for(int i = 0; i < 50; i++){
            if(String.valueOf(i).contains("1")) {
                listItems.add(new Place("street " + i, "zip " + i, "city " + i, R.drawable.route));
            } else {
                listItems.add(new Place("street " + i, "zip " + i, "city " + i, R.drawable.home));
            }
        }
        ArrayAdapter adapter = new PlaceAdapter(this, listItems);
        itemsView.setAdapter(adapter);

        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* Code pour audio au click
                try {
                    AssetFileDescriptor afd = getAssets().openFd("cris.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                Place item = (Place) parent.getItemAtPosition(position);
                String streetName = item.getStreet();
                Intent placeDetailsIntent = new Intent(getContext(), PlaceDetailsActivity.class);
                placeDetailsIntent.putExtra("streetName", streetName);
                startActivity(placeDetailsIntent);
            }
        });
    }

    private Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTask<String, Void, Response> asyncTask = new AsyncTask<String, Void, Response>() {
            @Override
            protected Response doInBackground(String... strings) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(strings[0])
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    // Silent catch
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                try {
                    Log.i(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncTask.execute(GET_URL);
    }
}
