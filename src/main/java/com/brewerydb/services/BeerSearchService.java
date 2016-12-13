package com.brewerydb.services;

import com.brewerydb.Constants;
import com.brewerydb.models.Beer;
import com.brewerydb.models.BeerSearchResult;
import com.brewerydb.models.SingleBeerSearchResult;

import com.Utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class BeerSearchService {
    public static void main(String[] args) {
        try {
            //SingleBeerSearchResult singleBeer = getBeerById("oeGSxs");

            BeerSearchResult result = findBeers("flying");

            ArrayList<Beer> beerList = result.getData();
            System.out.println("Results: " + result.getTotalResults());
            Beer beer = beerList.get(1);
            System.out.println(beer.getName());
            System.out.println(beer.getAbv());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Beer> searchResults(String name) {
        ArrayList<Beer> beerList = null;

        try {
            BeerSearchResult result = findBeers(name);

            beerList = result.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return beerList;
    }

    public static SingleBeerSearchResult getBeerById(String id) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BEER_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment(id);
        urlBuilder.addQueryParameter("key", Constants.BREWERY_DB_KEY);
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Set Date format for deserialization 2013-04-17 15:51:31
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(myDateFormat);

        SingleBeerSearchResult result = mapper.readValue(in, SingleBeerSearchResult.class);

        in.close();

        return result;
    }

    public static BeerSearchResult findBeers(String name) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BEER_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SEARCH_QUERY, name);
        urlBuilder.addQueryParameter("key", Constants.BREWERY_DB_KEY);
        urlBuilder.addQueryParameter("type", "beer");

        // Build string for url to request
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Set Date format for deserialization 2013-04-17 15:51:31
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(myDateFormat);

        BeerSearchResult result = mapper.readValue(in, BeerSearchResult.class);

        in.close();

        return result;
    }

    public static ArrayList<Beer> processResults(Response response){
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
