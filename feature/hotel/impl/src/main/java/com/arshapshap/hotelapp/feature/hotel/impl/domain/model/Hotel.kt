package com.arshapshap.hotelapp.feature.hotel.impl.domain.model

internal data class Hotel(
    val id: Int,
    val name: String,
    val adress: String,
    val minimalPrice: Int,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: AboutTheHotel
)

internal data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)