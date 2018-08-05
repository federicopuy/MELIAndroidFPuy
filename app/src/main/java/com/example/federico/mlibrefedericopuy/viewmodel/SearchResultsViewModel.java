package com.example.federico.mlibrefedericopuy.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.federico.mlibrefedericopuy.datasource.factory.ProductDataFactory;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchResultsViewModel {

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Product>> productLiveData;
    private String query;


    private AppController appController;
    public SearchResultsViewModel(@NonNull AppController appController, String query) {
        this.appController = appController;
        this.query = query;
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        ProductDataFactory productDataFactory = new ProductDataFactory(appController, query);

        networkState = Transformations.switchMap(productDataFactory.getMutableLiveData(),
                dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        productLiveData = (new LivePagedListBuilder(productDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<Product>> getProductLiveData() {
        return productLiveData;
    }



}
