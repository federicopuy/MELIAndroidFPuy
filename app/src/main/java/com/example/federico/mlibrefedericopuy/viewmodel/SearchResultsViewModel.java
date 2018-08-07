package com.example.federico.mlibrefedericopuy.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.federico.mlibrefedericopuy.datasource.factory.ProductDataFactory;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.utils.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchResultsViewModel extends ViewModel {

    /*
     * Capa intermedia entre ProductDataSource y Ui
     *
     * */

    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Product>> productLiveData;
    private String query = Constants.DEFAULT_SEARCH;
    private AppController appController;
    private ProductDataFactory productDataFactory;

    public SearchResultsViewModel(AppController appController) {
        this.appController = appController;
        init();
    }

    public void init() {
        productDataFactory = new ProductDataFactory(appController, query);
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

    public void invalidateDataSource() {
        //metodo para invalidar current DataSource y efectuar llamada con nueva query
        productDataFactory.setQuery(query);
        productDataFactory.getProductDataSource().invalidate();
    }

    public void setQuery(String query){
        this.query = query;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final AppController appController;

        public Factory(@NonNull AppController appController) {
            this.appController = appController;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new SearchResultsViewModel(appController);
        }
    }

}
