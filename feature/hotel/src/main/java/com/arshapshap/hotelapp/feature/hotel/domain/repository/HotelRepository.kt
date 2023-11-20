package com.arshapshap.hotelapp.feature.hotel.domain.repository

import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel

internal interface HotelRepository {

    suspend fun getHotel(): Hotel
}