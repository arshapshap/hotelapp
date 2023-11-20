package com.arshapshap.hotelapp.feature.booking.domain.usecase

import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.domain.repository.BookingRepository

internal class GetBookingDataUseCase(
    private val repository: BookingRepository
) {

    suspend operator fun invoke(): BookingData {
        return repository.getBookingData()
    }
}