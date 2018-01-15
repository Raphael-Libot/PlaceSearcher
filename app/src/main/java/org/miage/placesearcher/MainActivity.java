package org.miage.placesearcher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import org.miage.placesearcher.model.Place;
import org.miage.placesearcher.service.EventBusManager;
import org.miage.placesearcher.service.PlaceSearchService;
import org.miage.placesearcher.service.SearchResultEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String GET_URL = "https://api-adresse.data.gouv.fr/search/?q=Place%20du%20commerce";

    @BindView(R.id.items)
    ListView itemsView;

    private PlaceSearchService service;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        service = new PlaceSearchService();
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
        adapter = new PlaceAdapter(this, listItems);
        itemsView.setAdapter(adapter);

        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        service.searchPlaces(GET_URL);
        EventBusManager.BUS.register(this);
    }

    @Override
    protected void onPause() {
        EventBusManager.BUS.unregister(this);
        super.onPause();
    }

    @Subscribe
    public void searchResult(SearchResultEvent searchResultEvent) {
        adapter.clear();
        adapter.addAll(searchResultEvent.getPlaces());
    }
}
