package com.example.federico.mlibrefedericopuy.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    val client: ApiInterface
        get() {
            if (retrofit == null) {
                val client = OkHttpClient.Builder()
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(NetworkUtils.COMPLETEURL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
}
