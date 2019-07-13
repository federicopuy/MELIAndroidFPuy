package com.example.federico.mlibrefedericopuy.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting

class ProductListViewModel(private val repository: ProductListRepository) : ViewModel() {

    val queryLiveData = MutableLiveData<String>()

    val results: LiveData<ProductListState>
        get() = _results
    // With switchMap, very time queryLiveData changes, a new results LiveData is created and the previous one is destroyed
    private val _results = Transformations.switchMap(queryLiveData, repository::retrieveProducts)

    /**
     * Called whenever the user enters a new query
     */
    fun searchProducts(query: String) {
        queryLiveData.value = query
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearCompositeDisposable()
    }
}
