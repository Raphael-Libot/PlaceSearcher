package org.miage.placesearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rapha on 08/01/2018.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.textViewZipCode)
    TextView zipCode;

    @BindView(R.id.textViewStreet)
    TextView street;

    @BindView(R.id.textViewCity)
    TextView city;

    public PlaceAdapter(Context context, List<Place> places) {
        super(context, -1, places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View actualView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            actualView = inflater.inflate(R.layout.layout_listitems, parent, false);
        }
        ButterKnife.bind(this, actualView);
        zipCode.setText(getItem(position).getZipCode());
        street.setText(getItem(position).getStreet());
        city.setText(getItem(position).getCity());
        imageView.setImageResource(getItem(position).getImage());
        return actualView;
    }

}
