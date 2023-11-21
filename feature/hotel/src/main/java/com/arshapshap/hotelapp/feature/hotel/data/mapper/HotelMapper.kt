package com.arshapshap.hotelapp.feature.hotel.data.mapper

import com.arshapshap.hotelapp.feature.hotel.data.network.hotel.response.AboutTheHotelResponse
import com.arshapshap.hotelapp.feature.hotel.data.network.hotel.response.HotelResponse
import com.arshapshap.hotelapp.feature.hotel.domain.model.AboutTheHotel
import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel

internal class HotelMapper() {

    fun map(hotelResponse: HotelResponse): Hotel = with(hotelResponse) {
        Hotel(
            id = id ?: 0,
            name = name ?: "",
            adress = adress ?: "",
            minimalPrice = minimalPrice ?: 0,
            priceForIt = priceForIt ?: "",
            rating = rating ?: 0,
            ratingName = ratingName ?: "",
            imageUrls = imageUrls?.map { it ?: "" } ?: listOf(),
            aboutTheHotel = aboutTheHotel?.let { map(it) } ?: AboutTheHotel(
                description = "",
                peculiarities = listOf()
            )
        )
    }

    private fun map(aboutTheHotelResponse: AboutTheHotelResponse): AboutTheHotel = with(aboutTheHotelResponse) {
        AboutTheHotel(
            description = description ?: "",
            peculiarities = peculiarities?.map { it ?: "" } ?: listOf()
        )
    }
}