package com;

import com.brewerydb.models.*;
import com.yummly.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utils {
    // constants for brewerydb model and services
    public static final String API_ENDPOINT = "https://api.brewerydb.com/v2";
    public static final String BREWERY_DB_KEY = "65c4337e8d8b1b33639065e33c854cbf";
    public static final String BEER_SEARCH_BASE_URL = "http://api.brewerydb.com/v2/search?&type=beer";
    public static final String SEARCH_QUERY = "q";
    public static final String BEER_ENDPOINT = API_ENDPOINT + "/beer";


    // constants for yummly model and services
    public static final String YUMMLY_API_ENDPOINT = "http://api.yummly.com/v1/api/";
    public static final String YUMMLY_ID = "385fb868";
    public static final String YUMMLY_KEY = "ba3cd23fe375d062943ece58724148ab";
    public static final String YUMMLY_MAX_RESULTS = "50";

    // main function used for testing methods
    public static void main(String[] args) {
        Beer beer = new Beer("Carries best", "lkjei");
        beer.setFoodPairings("Chili, spicy curry chicken, creamy blue cheese");

        ArrayList<String> result = createRecipeLinksFromBeer(beer);
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean validInputString(String s) {
        boolean result = true;

        if (s == null || s.length() == 0) {
            result = false;
        }

        return result;
    }

    public static InputStream openConnection(String url) throws IOException {
        URL endpoint = new URL(url);

        System.out.println("Printing openConnection->endpoint: " + endpoint);

        URLConnection urlCon = endpoint.openConnection();

        return urlCon.getInputStream();
    }

    public static ArrayList<String> createRecipeLinksFromBeer(Beer beer) {
        ArrayList<String> result = new ArrayList<String>();

        String foodPairings = beer.getFoodPairings();

        List<String> foodList = Arrays.asList(foodPairings.split(", "));

        for (int i = 0; i < foodList.size(); i++) {
           System.out.println(foodList.get(i));
        }

        return result;
    }
}
