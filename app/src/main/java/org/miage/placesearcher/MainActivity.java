package org.miage.placesearcher;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
                try {
                    AssetFileDescriptor afd = getAssets().openFd("cris.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
