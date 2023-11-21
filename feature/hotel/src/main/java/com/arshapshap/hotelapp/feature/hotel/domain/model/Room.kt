package com.arshapshap.hotelapp.feature.hotel.domain.model

internal data class Room(
    val id: Int,
    val name: String,
    val price: Int,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
)