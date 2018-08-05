package com.example.federico.mlibrefedericopuy.datasource.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.federico.mlibrefedericopuy.datasource.ProductDataSource;
import com.example.federico.mlibrefedericopuy.AppController;

public class ProductDataFactory extends DataSource.Factory{


    private MutableLiveData<ProductDataSource> mutableLiveData;
    private AppController appController;
    private String query;

    public ProductDataFactory(AppController appController, String query) {
        this.appController = appController;
        this.mutableLiveData = new MutableLiveData<ProductDataSource>();
        this.query = query;
    }

    @Override
    public DataSource create() {
        ProductDataSource productDataSource = new ProductDataSource(appController, query);
        mutableLiveData.postValue(productDataSource);
        return productDataSource;
    }

    public MutableLiveData<ProductDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
