package com.example.federico.mlibrefedericopuy.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.federico.mlibrefedericopuy.AppController;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.network.ApiInterface;
import com.example.federico.mlibrefedericopuy.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInfoDataSource {

    private AppController appController;

    public ProductInfoDataSource(AppController appController) {
        this.appController = appController;
    }

    public LiveData<Description> loadProductDescription(String productId) {

        final MutableLiveData<Description> data = new MutableLiveData<>();

        appController.getApiInterface().getItemDescription(productId)
                .enqueue(new Callback<Description>() {
                    @Override
                    public void onResponse(Call<Description> call, Response<Description> response) {

                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        } else {
                            //todo handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<Description> call, Throwable t) {

                        //todo handle error
                    }
                });

        return data;
    }
}

