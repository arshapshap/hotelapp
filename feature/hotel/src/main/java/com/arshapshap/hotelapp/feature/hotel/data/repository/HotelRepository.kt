package com.arshapshap.hotelapp.feature.hotel.data.repository

import com.arshapshap.hotelapp.feature.hotel.data.mapper.HotelMapper
import com.arshapshap.hotelapp.feature.hotel.data.network.hotel.HotelApi
import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel
import com.arshapshap.hotelapp.feature.hotel.domain.repository.HotelRepository

internal class HotelRepositoryImpl(
    private val api: HotelApi,
    private val mapper: HotelMapper
) : HotelRepository {

    override suspend fun getHotel(): Hotel {
        return mapper.map(api.getHotel())
    }
}