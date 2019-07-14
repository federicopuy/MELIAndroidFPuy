package com.example.federico.mlibrefedericopuy.utils

object Utils {

    fun getFormattedPrice(price: Double?): String {

        return try {
            "$ " + String.format("%.2f", price)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }

    fun getCondition(englishCondition: String): String {

        return if (englishCondition == "new") {
            "Nuevo"
        } else {
            "Usado"
        }
    }

    fun getFormattedRatingAverage(ratingAverage: Double?): String {
        return "(" + ratingAverage.toString() + ")"
    }

    fun getAvailableQuantity(availableQuantity: Long?): String {
        return availableQuantity.toString() + " Disponibles"
    }

    fun getSoldAmount(soldAmount: Long?): String? {
        soldAmount?.let {
            if (it > 0) {
                return "$it vendidos"
            }
        }
        return null
    }
}
