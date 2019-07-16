package com.example.federico.mlibrefedericopuy.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.federico.mlibrefedericopuy.model.Description
import com.example.federico.mlibrefedericopuy.model.Item
import com.example.federico.mlibrefedericopuy.network.RetrofitClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductDetailRepository {

    private val compositeDisposable = CompositeDisposable()
    private val client = RetrofitClient.client

    val loadingStatus : LiveData<Boolean>
        get() = _loadingStatus
    private val _loadingStatus = MutableLiveData<Boolean>()

    fun retrieveDescription(productId: String): MutableLiveData<Description> {
        val productDescriptionLiveData = MutableLiveData<Description>()

        client.getItemDescription(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loadingStatus.value = true }
                .doAfterTerminate { _loadingStatus.value = false }
                .subscribe(object : SingleObserver<Description> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(description: Description) {
                        productDescriptionLiveData.value = description
                    }

                    override fun onError(e: Throwable) {
                        // TODO HANDLE ERROR
                    }
                })
        return productDescriptionLiveData
    }

    fun retrieveProductImages(productId: String): MutableLiveData<Item> {
        val itemLiveData = MutableLiveData<Item>()

        client.getItem(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loadingStatus.value = true }
                .doAfterTerminate { _loadingStatus.value = false }
                .subscribe(object : SingleObserver<Item> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onSuccess(item: Item) {
                        itemLiveData.value = item
                    }

                    override fun onError(e: Throwable) {
                        // TODO HANDLE ERROR
                    }
                })
        return itemLiveData
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}

