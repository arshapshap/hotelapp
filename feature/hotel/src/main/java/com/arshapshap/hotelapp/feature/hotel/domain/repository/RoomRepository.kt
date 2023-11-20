package com.arshapshap.hotelapp.feature.hotel.domain.repository

import com.arshapshap.hotelapp.feature.hotel.domain.model.Room

internal interface RoomRepository {

    suspend fun getRooms(): List<Room>
}