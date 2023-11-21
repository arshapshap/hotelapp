package com.arshapshap.hotelapp.feature.hotel.domain.usecase

import com.arshapshap.hotelapp.feature.hotel.domain.model.Hotel
import com.arshapshap.hotelapp.feature.hotel.domain.repository.HotelRepository

internal class GetHotelUseCase(
    private val repository: HotelRepository
) {

    suspend operator fun invoke(): Hotel {
        return repository.getHotel()
    }
}