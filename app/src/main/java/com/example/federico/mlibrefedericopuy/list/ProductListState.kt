package com.example.federico.mlibrefedericopuy.list

import com.example.federico.mlibrefedericopuy.model.ProductInfo

sealed class ProductListState {
    class ShowItems(val products: List<ProductInfo>) : ProductListState()
    class Loading(val isLoading: Boolean) : ProductListState()
    companion object ShowError : ProductListState()
}