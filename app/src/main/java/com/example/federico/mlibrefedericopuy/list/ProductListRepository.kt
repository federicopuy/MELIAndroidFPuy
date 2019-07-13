package com.example.federico.mlibrefedericopuy.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.federico.mlibrefedericopuy.model.Product
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import com.example.federico.mlibrefedericopuy.network.RetrofitClient
import com.example.federico.mlibrefedericopuy.utils.Utils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductListRepository {

    private val compositeDisposable = CompositeDisposable()
    private val client = RetrofitClient.client

    fun retrieveProducts(query: String): LiveData<ProductListState> {
        val stateLiveData = MutableLiveData<ProductListState>()

        client.getSearchResults(query, 10, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { stateLiveData.value = ProductListState.Loading(true) }
                .doAfterTerminate { stateLiveData.value = ProductListState.Loading(false) }
                .map { productMapper(it.products) }
                .subscribe(object : SingleObserver<List<ProductInfo>> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(results: List<ProductInfo>) {
                        stateLiveData.value = ProductListState.ShowItems(results)
                    }

                    override fun onError(e: Throwable) {
                        stateLiveData.value = ProductListState.ShowError
                    }
                })
        return stateLiveData
    }

    private fun productMapper(productsList: List<Product>): List<ProductInfo> {
        val productInfo = mutableListOf<ProductInfo>()
        productsList.forEach {
            productInfo += ProductInfo(
                    it.id,
                    it.title,
                    Utils.getFormattedPrice(it.price),
                    Utils.getAvailableQuantity(it.availableQuantity),
                    Utils.getSoldAmount(it.soldQuantity),
                    Utils.getCondition(it.condition),
                    it.thumbnail,
                    it.acceptsMercadopago)
        }
        return productInfo
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}