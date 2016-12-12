package com.yummly.services;

import com.yummly.models.*;
import com.yummly.Constants;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    }

    public static Recipe getRecipeById(String id) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipe");
        urlBuilder.addPathSegment(id);
        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Constants.YUMMLY_KEY);
        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = performRequest(url);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Recipe result = mapper.readValue(in, Recipe.class);

        in.close();

        return result;
    }

    public static SearchResult findRecipes(String name) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_API_ENDPOINT).newBuilder();
        urlBuilder.addPathSegment("recipes");
        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_ID);
        urlBuilder.addQueryParameter("_app_key", Constants.YUMMLY_KEY);
        urlBuilder.addQueryParameter("maxResult", Constants.YUMMLY_MAX_RESULTS);
        urlBuilder.addEncodedQueryParameter("q", name);

        String url = urlBuilder.build().toString();

        // Perform search request.
        InputStream in = performRequest(url);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SearchResult result = mapper.readValue(in, SearchResult.class);

        in.close();

        return result;
    }

    private static InputStream performRequest(String url) throws IOException {
        URL endpoint = new URL(url);

        System.out.println("Printing endpoint:");
        System.out.println(endpoint);

        URLConnection urlCon = endpoint.openConnection();

        return urlCon.getInputStream();
    }
}