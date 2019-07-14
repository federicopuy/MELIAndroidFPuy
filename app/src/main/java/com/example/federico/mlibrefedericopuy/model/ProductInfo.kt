package com.example.federico.mlibrefedericopuy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductInfo(val id: String, val title: String, val price: String, val availableQuantity: String,
                       val soldQuantity: String?, val condition: String, val thumbnail: String?,
                       val acceptsMp: Boolean) : Parcelable