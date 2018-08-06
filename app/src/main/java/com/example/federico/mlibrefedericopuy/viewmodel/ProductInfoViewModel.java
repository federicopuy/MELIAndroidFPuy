package com.example.federico.mlibrefedericopuy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.federico.mlibrefedericopuy.AppController;
import com.example.federico.mlibrefedericopuy.datasource.ProductInfoDataSource;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.model.Item;

import java.util.List;

public class ProductInfoViewModel extends ViewModel{

    private AppController appController;

    private  LiveData<Description> productDescription;
    private LiveData<List<String>> picturesUrls;

    public ProductInfoViewModel(AppController appController, String productId) {
        this.appController = appController;
        ProductInfoDataSource productInfoDataSource = new ProductInfoDataSource(appController);
        productDescription = productInfoDataSource.loadProductDescription(productId);
        picturesUrls = productInfoDataSource.loadItem(productId);
    }

    public LiveData<Description> getProductDescription(){
        return productDescription;
    }

    public LiveData<List<String>> getPicturesUrls() {return picturesUrls;};

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
