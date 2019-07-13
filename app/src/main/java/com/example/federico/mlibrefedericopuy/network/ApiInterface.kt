package com.example.federico.mlibrefedericopuy.network

import com.example.federico.mlibrefedericopuy.model.Description
import com.example.federico.mlibrefedericopuy.model.Item
import com.example.federico.mlibrefedericopuy.model.SearchResults

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(NetworkUtils.SITE_ARGENTINA + "search")
    fun getSearchResults(@Query("q") searchParameter: String,
                         @Query("offset") offset: Long,
                         @Query("limit") limit: Long): Single<SearchResults>

    @GET(NetworkUtils.ITEMS + "{productId}/" + NetworkUtils.DESCRIPTION)
    fun getItemDescription(@Path("productId") productId: String): Single<Description>

    @GET(NetworkUtils.ITEMS + "{productId}")
    fun getItem(@Path("productId") productId: String): Single<Item>
}
