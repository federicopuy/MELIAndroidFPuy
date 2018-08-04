package com.example.federico.mlibrefedericopuy.network;

import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.model.Item;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.model.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(NetworkUtils.SITE_ARGENTINA +"search")
    Call<SearchResults> getSearchResults(@Query("q") String searchParameter);

    @GET(NetworkUtils.ITEMS + "{productId}/" + NetworkUtils.DESCRIPTION)
    Call<Description> getItemDescription(@Path("productId") String productId);


    @GET(NetworkUtils.ITEMS + "{productId}")
    Call<Item> getItem(@Path("productId") String productId);
}
