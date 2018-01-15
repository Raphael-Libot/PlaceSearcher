package org.miage.placesearcher.service;

import org.miage.placesearcher.model.Place;

import java.util.List;

/**
 * Created by Simon on 15/01/2018.
 */

public class SearchResultEvent {

    private List<Place> places;

    public SearchResultEvent(List<Place> places) {
        this.places = places;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
