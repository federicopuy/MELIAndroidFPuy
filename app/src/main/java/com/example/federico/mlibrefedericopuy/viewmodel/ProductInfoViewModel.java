package com.example.federico.mlibrefedericopuy.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.datasource.ProductInfoDataSource;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.network.NetworkState;

import java.util.List;

public class ProductInfoViewModel extends ViewModel{

    private AppController appController;
    private LiveData<NetworkState> networkState;
    private  LiveData<Description> productDescription;
    private LiveData<List<String>> picturesUrls;

    public ProductInfoViewModel(AppController appController, String productId) {
        this.appController = appController;
        ProductInfoDataSource productInfoDataSource = new ProductInfoDataSource(appController);
        productDescription = productInfoDataSource.loadProductDescription(productId);
        picturesUrls = productInfoDataSource.loadItem(productId);
        networkState = productInfoDataSource.getNetworkState();
    }

    public LiveData<Description> getProductDescription(){
        return productDescription;
    }

    public LiveData<List<String>> getPicturesUrls() {return picturesUrls;}

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final AppController appController;
        private final String productId;

        public Factory(@NonNull AppController appController, String productId) {
            this.appController = appController;
            this.productId = productId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProductInfoViewModel(appController, productId);
        }

    }



}
