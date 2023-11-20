package com.arshapshap.hotelapp.feature.booking.data.repository

import com.arshapshap.hotelapp.feature.booking.data.mapper.BookingMapper
import com.arshapshap.hotelapp.feature.booking.data.network.BookingApi
import com.arshapshap.hotelapp.feature.booking.domain.model.BookingData
import com.arshapshap.hotelapp.feature.booking.domain.repository.BookingRepository

internal class BookingRepositoryImpl(
    private val api: BookingApi,
    private val mapper: BookingMapper
) : BookingRepository {

    override suspend fun getBookingData(): BookingData {
        return mapper.map(api.getBookingData())
    }
}