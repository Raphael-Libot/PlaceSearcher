package org.miage.placesearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

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
        String[] listItems = new String[]{"Veni","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici","Vidi","Vici"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        itemsView.setAdapter(adapter);
    }


}
