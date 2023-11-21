package com.arshapshap.hotelapp.feature.hotel.data.network.room

import com.arshapshap.hotelapp.feature.hotel.data.network.room.response.RoomsResponse
import retrofit2.http.GET

internal interface RoomApi {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): RoomsResponse
}