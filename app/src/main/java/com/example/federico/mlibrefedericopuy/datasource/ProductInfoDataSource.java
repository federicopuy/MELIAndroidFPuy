package com.example.federico.mlibrefedericopuy.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.model.Item;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInfoDataSource {

    private AppController appController;
    private MutableLiveData networkState;


    public ProductInfoDataSource(AppController appController) {
        this.appController = appController;
        networkState = new MutableLiveData();
    }

    public LiveData<Description> loadProductDescription(String productId) {

        final MutableLiveData<Description> data = new MutableLiveData<>();
        networkState.postValue(NetworkState.LOADING);

        appController.getApiInterface().getItemDescription(productId)
                .enqueue(new Callback<Description>() {
                    @Override
                    public void onResponse(Call<Description> call, Response<Description> response) {

                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                            networkState.postValue(NetworkState.LOADED);
                        } else {
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Description> call, Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });

        return data;
    }

    public LiveData<List<String>> loadItem(String productId) {

        final MutableLiveData<List<String>> data = new MutableLiveData<>();

        appController.getApiInterface().getItem(productId)
                .enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(Call<Item> call, Response<Item> response) {
                        if (response.isSuccessful()) {
                            data.setValue(Utils.getPicturesList(response.body()));
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Item> call, Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    }
                });
       return data;
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

}

