package com.arshapshap.hotelapp.feature.booking.domain.repository

import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData

internal interface BookingRepository {

    suspend fun getBookingData(): BookingData
}