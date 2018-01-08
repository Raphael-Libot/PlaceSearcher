package org.miage.placesearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

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
            listItems.add(new Place( "street " + i, "zip " + i, "city " + i ));
        }
        ArrayAdapter adapter = new PlaceAdapter(this, listItems);
        itemsView.setAdapter(adapter);
    }


}
