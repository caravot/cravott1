package com.brewerydb.services;

import com.brewerydb.models.Beer;
import com.brewerydb.models.BeerSearchResult;
import com.brewerydb.models.SingleBeerSearchResult;

import com.Utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class BeerSearchService {
    /**
     * Main class primarily used for testing
     * @param args
     */
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

    /**
     * Search for a single beer
     * @param id
     * @return SingleBeerResult
     * @throws IOException
     */
    public static SingleBeerSearchResult getBeerById(String id) throws IOException {
        // error checking; ensure name is valid; return empty class
        if (!Utils.validInputString(id)) {
            return new SingleBeerSearchResult();
        }

        // build url to API
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.BEER_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment(id);
        urlBuilder.addQueryParameter("key", Utils.BREWERY_DB_KEY);

        // Build string for url to request
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Set Date format for deserialization (ex. 2013-04-17 15:51:31)
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Create JSON result parser
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(myDateFormat);

        // Parse results into class
        SingleBeerSearchResult result = mapper.readValue(in, SingleBeerSearchResult.class);

        // close open HTTP connection
        in.close();

        return result;
    }

    /**
     * findBeers
     * @param name
     * @return BeerSearchResult
     * @throws IOException
     */
    public static BeerSearchResult findBeers(String name) throws IOException {
        // error checking; ensure name is valid; return empty class
        if (!Utils.validInputString(name)) {
            return new BeerSearchResult();
        }

        // build url to API
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.BEER_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Utils.SEARCH_QUERY, name);
        urlBuilder.addQueryParameter("key", Utils.BREWERY_DB_KEY);
        urlBuilder.addQueryParameter("type", "beer");

        // Build string for url to request
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Set Date format for deserialization (ex. 2013-04-17 15:51:31)
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Create JSON result parser
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(myDateFormat);

        // Parse results into class
        BeerSearchResult result = mapper.readValue(in, BeerSearchResult.class);

        // close open HTTP connection
        in.close();

        return result;
    }
}
