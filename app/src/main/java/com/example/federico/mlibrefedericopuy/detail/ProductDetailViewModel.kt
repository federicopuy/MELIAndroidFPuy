package com.example.federico.mlibrefedericopuy.detail

import android.arch.lifecycle.*
import com.example.federico.mlibrefedericopuy.model.Description
import com.example.federico.mlibrefedericopuy.model.Item

class ProductDetailViewModel(private val repository: ProductDetailRepository, productId: String) : ViewModel() {

    val productDescription: LiveData<Description>
        get() = _productDescription
    private val _productDescription = repository.retrieveDescription(productId)

    val loadingStatus: LiveData<Boolean>
        get() = _loadingStatus
    private val _loadingStatus = repository.loadingStatus

    val productImages: LiveData<List<String>>
        get() = _productImages
    //LiveData from repository returns an Item object. We need to extract the list of urls from that item, so we use
    // a map Transformation to apply the imageMapper() function to the object.
    private val _productImages = Transformations.map(repository.retrieveProductImages(productId), ::imageMapper)

    /**
     * Extracts a list of urls from an Item object.
     */
     fun imageMapper(item: Item): List<String> {
        val pictureUrls = mutableListOf<String>()
        item.pictures.forEach {
            pictureUrls += it.url
        }
        return pictureUrls
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearCompositeDisposable()
    }

}

