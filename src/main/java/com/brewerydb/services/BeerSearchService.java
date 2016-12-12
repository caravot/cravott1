package com.brewerydb.services;

import com.brewerydb.Constants;
import com.brewerydb.models.Beer;
import com.brewerydb.models.SearchResult;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BeerSearchService {
    public static final String TAG = BeerSearchService.class.getSimpleName();

    public static void main(String[] args) {
        ArrayList<Beer> beerList = searchResults("flying");
    }

    public static ArrayList<Beer> searchResults(String name) {
        ArrayList<Beer> beerList = null;

        try {
            SearchResult result = findBeers(name);

            beerList = result.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return beerList;
    }

    private static InputStream performRequest(String url) throws IOException {
        URL endpoint = new URL(url);

        System.out.println("Printing endpoint:");
        System.out.println(endpoint);

        URLConnection urlCon = endpoint.openConnection();

        return urlCon.getInputStream();
    }

    //public static void findBeers(String name, Callback callback) {
    public static SearchResult findBeers(String name) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BEER_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SEARCH_QUERY, name);
        urlBuilder.addQueryParameter("key", Constants.BREWERY_DB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        // Perform search request.
        InputStream in = performRequest(url);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SearchResult result = mapper.readValue(in, SearchResult.class);

        in.close();

        return result;

        //Call call = client.newCall(request);
        //call.enqueue(callback);
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
