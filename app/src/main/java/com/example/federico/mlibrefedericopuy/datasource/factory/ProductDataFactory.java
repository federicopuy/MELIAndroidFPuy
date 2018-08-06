package com.example.federico.mlibrefedericopuy.datasource.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.federico.mlibrefedericopuy.datasource.ProductDataSource;
import com.example.federico.mlibrefedericopuy.network.AppController;

public class ProductDataFactory extends DataSource.Factory{

    private MutableLiveData<ProductDataSource> mutableLiveData;
    private AppController appController;
    private String query;
    private ProductDataSource productDataSource;

    public ProductDataFactory(AppController appController, String query) {
        this.appController = appController;
        this.mutableLiveData = new MutableLiveData<ProductDataSource>();
        this.query = query;
    }

    @Override
    public DataSource create() {
        productDataSource = new ProductDataSource(appController, query);
        mutableLiveData.postValue(productDataSource);
        return productDataSource;
    }

    public MutableLiveData<ProductDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public ProductDataSource getProductDataSource() {
        return productDataSource;
    }

    public void setQuery(String q){
        query = q;
    }
}
