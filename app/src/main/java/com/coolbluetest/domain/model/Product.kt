package com.coolbluetest.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("USPs")
    val usps: List<String>?,
    @SerializedName("reviewInformation")
    val reviewInformation: ReviewInformation,
    @SerializedName("availabilityState")
    val availabilityState: Int,
    @SerializedName("salesPriceIncVat")
    val salesPrice: Double,
    @SerializedName("listPriceIncVat")
    val retailPrice: Double?,
    @SerializedName("productImage")
    val productImage: String,
    @SerializedName("nextDayDelivery")
    val isNextDayDelivery: Boolean
)

data class ReviewInformation(
    @SerializedName("reviewSummary")
    val reviewSummary: ReviewSummary
)

data class ReviewSummary(
    @SerializedName("reviewAverage")
    val reviewAverage: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int
)
