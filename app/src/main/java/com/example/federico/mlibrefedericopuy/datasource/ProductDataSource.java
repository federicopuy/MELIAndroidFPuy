package com.example.federico.mlibrefedericopuy.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.model.SearchResults;
import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Long, Product> {

    private static final String TAG = ProductDataSource.class.getSimpleName();

    private AppController appController;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;
    private String query;


    public ProductDataSource(AppController appController, String query) {
        this.appController = appController;
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        this.query = query;
    }


    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Product> callback) {


        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        appController.getApiInterface().getSearchResults(query, 0, NetworkUtils.LIMIT).
                enqueue(new Callback<SearchResults>() {
                    @Override
                    public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                        if (response.isSuccessful()) {

                            callback.onResult(response.body().getProducts(),null, Long.valueOf(NetworkUtils.LIMIT));
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResults> call, Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Product> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Product> callback) {

        Log.i(TAG, "Loading Range " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        appController.getApiInterface().getSearchResults(query, params.key, params.requestedLoadSize)
        .enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if (response.isSuccessful()) {

                    long nextKey = (params.key == response.body().getPaging().getTotal()) ? null : params.key + NetworkUtils.LIMIT;
                    callback.onResult(response.body().getProducts(), nextKey);
                    networkState.postValue(NetworkState.LOADED);

                } else {
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });


    }

}
