package com.example.federico.mlibrefedericopuy.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Product(
        @SerializedName("id") var id: String,
        @SerializedName("title") var title: String,
        @SerializedName("price") var price: Double = 0.toDouble(),
        @SerializedName("available_quantity") var availableQuantity: Long? = null,
        @SerializedName("sold_quantity") var soldQuantity: Long = 0,
        @SerializedName("condition") val condition: String,
        @SerializedName("thumbnail") var thumbnail: String? = null,
        @SerializedName("accepts_mercadopago") var acceptsMercadopago: Boolean = false,
        @SerializedName("original_price") var originalPrice: Float? = null
)
