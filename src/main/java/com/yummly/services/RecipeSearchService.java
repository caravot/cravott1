package com.yummly.services;

import com.yummly.models.*;

import com.Utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeSearchService {
    public static final String TAG = RecipeSearchService.class.getSimpleName();

    public static void main(String[] args) {
        try {
            Recipe results = getRecipeById("Hot-Turkey-Salad-Sandwiches-Allrecipes");

            System.out.println(results.getName());
            System.out.println(results.getAttribution().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Yummly y = new Yummly("385fb868", "ba3cd23fe375d062943ece58724148ab");

        SearchResult result;

        Flavors minFlavors = new Flavors();
        minFlavors.setPiquant(0.8);
        ArrayList<NutritionRange> nutrition = new ArrayList<NutritionRange>();
        NutritionRange range = new NutritionRange();
        range.setMin(5);
        range.setMax(100);
        range.setNutrition(NutritionRange.NUTRITION.PROCNT);
        nutrition.add(range);
        //result = y.search("Chicken", true, null, null, -1, minFlavors, null, true, true, nutrition);

        //Recipe recipe = y.getRecipe(result.getMatches().get(0).getId());
        //System.out.println(String.format("%s, (%d), (%s) %s", recipe.getName(), recipe.getRating(), recipe.getId(), recipe.getImages().get(0).getHostedSmallUrl()));
    }

    public static Recipe getRecipeById(String id) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipe");
        urlBuilder.addPathSegment(id);
        urlBuilder.addQueryParameter("_app_id", Utils.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Utils.YUMMLY_KEY);
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Recipe result = mapper.readValue(in, Recipe.class);

        in.close();

        return result;
    }

    public static SearchResult findRecipes(String name) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipes");
        urlBuilder.addQueryParameter("_app_id", Utils.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Utils.YUMMLY_KEY);
        urlBuilder.addQueryParameter("maxResult", Utils.YUMMLY_MAX_RESULTS);
        urlBuilder.addEncodedQueryParameter("q", name);

        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SearchResult result = mapper.readValue(in, SearchResult.class);

        in.close();

        return result;
    }
}
