package com.arshapshap.hotelapp.feature.hotel.data.network.room.response

import com.google.gson.annotations.SerializedName

internal data class RoomResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("price_per")
    val pricePer: String?,
    @SerializedName("peculiarities")
    val peculiarities: List<String?>?,
    @SerializedName("image_urls")
    val imageUrls: List<String?>?
)