package com.brewerydb;

public class Constants {
    private static final String API_ENDPOINT = "https://api.brewerydb.com/v2";

    public static final String BREWERY_DB_KEY = "65c4337e8d8b1b33639065e33c854cbf";

    public static final String BEER_SEARCH_BASE_URL = "http://api.brewerydb.com/v2/search?&type=beer";
    public static final String BREWERY_SEARCH_BASE_URL = "http://api.brewerydb.com/v2/search?&type=brewery";

    public static final String SEARCH_QUERY = "q";

    public static final String BEERS_ENDPOINT = API_ENDPOINT + "/beers";
    public static final String BEER_ENDPOINT = API_ENDPOINT + "/beer";
    public static final String RANDOM_BEER_ENDPOINT = BEER_ENDPOINT + "/random";
    public static final String BREWERIES_ENDPOINT = API_ENDPOINT + "/breweries";
    public static final String BREWERY_ENDPOINT = API_ENDPOINT + "/brewery";
    public static final String FEATURED_ENDPOINT = API_ENDPOINT + "/featured";
    public static final String FEATURES_ENDPOINT = API_ENDPOINT + "/features/";
    public static final String CATEGORIES_ENDPOINT = API_ENDPOINT + "/categories";
    public static final String CATEGORY_ENDPOINT = API_ENDPOINT + "/category";
    public static final String STYLES_ENDPOINT = API_ENDPOINT + "/styles";
    public static final String STYLE_ENDPOINT = API_ENDPOINT + "/style";
    public static final String INGREDIENTS_ENDPOINT = API_ENDPOINT + "/ingredients";
    public static final String INGREDIENT_ENDPOINT = API_ENDPOINT + "/ingredient";
}
