package com.example.federico.mlibrefedericopuy.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ProductListViewModel(private val repository: ProductListRepository) : ViewModel() {

    val queryLiveData = MutableLiveData<String>()

    val results: LiveData<ProductListState>
        get() = _results
    // With switchMap, very time queryLiveData changes, a new results LiveData is created and the previous one is destroyed
    private val _results = Transformations.switchMap(queryLiveData, repository::retrieveProducts)

    /**
     * Called whenever the user enters a new query
     */
    fun searchProducts(query: CharSequence) {
        queryLiveData.value = query.toString()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearCompositeDisposable()
    }
}
