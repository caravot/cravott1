package com.yummly.services;

import com.yummly.models.*;

import com.Utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;

import java.io.IOException;
import java.io.InputStream;

public class RecipeSearchService {
    /**
     * Main class primarily used for testing
     * @param args
     */
    public static void main(String[] args) {
        try {
            Recipe results = getRecipeById("Hot-Turkey-Salad-Sandwiches-Allrecipes");

            System.out.println(results.getName());
            System.out.println(results.getAttribution().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search for a recipe by ID given
     * @param id
     * @return Recipe
     * @throws IOException
     */
    public static Recipe getRecipeById(String id) throws IOException {
        // build url to API
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipe");
        urlBuilder.addPathSegment(id);
        urlBuilder.addQueryParameter("_app_id", Utils.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Utils.YUMMLY_KEY);

        // Build string for url to request
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Create JSON result parser
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Parse results into class
        Recipe result = mapper.readValue(in, Recipe.class);

        // close open HTTP connection
        in.close();

        return result;
    }

    /**
     * Search for recipes by a given string
     * @param name
     * @return
     * @throws IOException
     */
    public static SearchResult findRecipes(String name) throws IOException {
        // build url to API
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Utils.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipes");
        urlBuilder.addQueryParameter("_app_id", Utils.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Utils.YUMMLY_KEY);
        urlBuilder.addQueryParameter("maxResult", Utils.YUMMLY_MAX_RESULTS);
        urlBuilder.addEncodedQueryParameter("q", name);

        // Build string for url to request
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = Utils.openConnection(url);

        // Create JSON result parser
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Parse results into class
        SearchResult result = mapper.readValue(in, SearchResult.class);

        // close open HTTP connection
        in.close();

        return result;
    }
}
