package com.arshapshap.hotelapp.feature.booking.data.network

import com.arshapshap.hotelapp.feature.booking.data.network.response.BookingDataResponse
import retrofit2.http.GET

internal interface BookingApi {

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBookingData(): BookingDataResponse
}