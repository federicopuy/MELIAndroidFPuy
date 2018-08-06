package com.example.federico.mlibrefedericopuy.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.federico.mlibrefedericopuy.datasource.ProductDataSource;
import com.example.federico.mlibrefedericopuy.datasource.ProductInfoDataSource;
import com.example.federico.mlibrefedericopuy.datasource.factory.ProductDataFactory;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchResultsViewModel extends ViewModel {

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Product>> productLiveData;
    private String query;
    ProductDataFactory productDataFactory;
    PagedList.Config pagedListConfig;
    private AppController appController;

    public SearchResultsViewModel(@NonNull AppController appController, String query) {
        this.appController = appController;
        productDataFactory = new ProductDataFactory(appController, query);

        init();

    }


    public void init() {


        executor = Executors.newFixedThreadPool(5);

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

    public void invalidateDataSource(String q) {


        productDataFactory.getProductDataSource().invalidate();

        productDataFactory = new ProductDataFactory(appController, q);
        executor = Executors.newFixedThreadPool(5);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        productLiveData = (new LivePagedListBuilder(productDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();

    }

}
