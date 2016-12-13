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

/**
 * Created by carrie on 12/12/16.
 */
public class Utils {
    public static void main(String[] args) {
        Beer beer = new Beer("Carries best", "lkjei");
        beer.setFoodPairings("Chili, spicy curry chicken, creamy blue cheese");

        ArrayList<String> result = createRecipeLinksFromBeer(beer);
    }

    public static InputStream openConnection(String url) throws IOException {
        URL endpoint = new URL(url);

        System.out.println("Printing endpoint:");
        System.out.println(endpoint);

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
