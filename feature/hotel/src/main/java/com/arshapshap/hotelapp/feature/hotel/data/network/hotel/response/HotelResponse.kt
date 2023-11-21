package com.arshapshap.hotelapp.feature.hotel.data.network.hotel.response

import com.google.gson.annotations.SerializedName

internal data class HotelResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("adress")
    val adress: String?,
    @SerializedName("minimal_price")
    val minimalPrice: Int?,
    @SerializedName("price_for_it")
    val priceForIt: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("rating_name")
    val ratingName: String?,
    @SerializedName("image_urls")
    val imageUrls: List<String?>?,
    @SerializedName("about_the_hotel")
    val aboutTheHotel: AboutTheHotelResponse?
)

internal data class AboutTheHotelResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("peculiarities")
    val peculiarities: List<String?>?
)