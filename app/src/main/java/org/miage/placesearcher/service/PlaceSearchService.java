package org.miage.placesearcher.service;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.miage.placesearcher.R;
import org.miage.placesearcher.model.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Simon on 15/01/2018.
 */

public class PlaceSearchService {

    private static final String TAG = PlaceSearchService.class.getName();

    public void searchPlaces(String url) {
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
                List<Place> places = new ArrayList<>();
                try {
                    String responseBody = response.body().string();
                    Log.i(TAG, responseBody);
                    JSONObject bodyObject = new JSONObject(responseBody);
                    JSONArray features = bodyObject.getJSONArray("features");
                    for (int i = 0; i < features.length(); i++) {
                        Place place = new Place();
                        JSONObject feature = (JSONObject) features.get(i);
                        JSONObject properties = feature.getJSONObject("properties");
                        String label = properties.getString("label");
                        String cityCode = properties.getString("citycode");
                        String city = properties.getString("city");
                        place.setStreet(label);
                        place.setZipCode(cityCode);
                        place.setCity(city);
                        place.setLatitude(0);
                        place.setLongitude(0);
                        place.setImage(R.drawable.home);
                        places.add(place);
                        Log.i(TAG, label);
                    }
                    EventBusManager.BUS.post(new SearchResultEvent(places));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncTask.execute(url);
    }
}
