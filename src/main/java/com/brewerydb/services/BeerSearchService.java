package com.brewerydb.services;

import com.brewerydb.Constants;
import com.brewerydb.models.Beer;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class BeerSearchService {
    public static final String TAG = BeerSearchService.class.getSimpleName();


    public static void findBeers(String name, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BEER_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SEARCH_QUERY, name);
        urlBuilder.addQueryParameter("key", Constants.BREWERY_DB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Beer> processResults(Response response){
        ArrayList<Beer> beers = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {
                JSONObject brewerydbJSON = new JSONObject(jsonData);
                JSONArray beersJSON = brewerydbJSON.getJSONArray("data");
                for(int i = 0; i < beersJSON.length(); i++){
                    JSONObject beerJSON = beersJSON.getJSONObject(i);

                    String name = beerJSON.getString("name");
                    String id = beerJSON.getString("id");

                    Beer beer = new Beer(name, id);
                    beers.add(beer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
