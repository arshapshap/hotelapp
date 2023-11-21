package com.arshapshap.hotelapp.feature.hotel.data.network.hotel

import com.arshapshap.hotelapp.feature.hotel.data.network.hotel.response.HotelResponse
import retrofit2.http.GET

internal interface HotelApi {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelResponse
}