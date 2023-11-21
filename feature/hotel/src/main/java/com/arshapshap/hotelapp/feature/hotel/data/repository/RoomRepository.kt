package com.arshapshap.hotelapp.feature.hotel.data.repository

import com.arshapshap.hotelapp.feature.hotel.data.mapper.RoomMapper
import com.arshapshap.hotelapp.feature.hotel.data.network.room.RoomApi
import com.arshapshap.hotelapp.feature.hotel.domain.model.Room
import com.arshapshap.hotelapp.feature.hotel.domain.repository.RoomRepository

internal class RoomRepositoryImpl(
    private val api: RoomApi,
    private val mapper: RoomMapper
) : RoomRepository {

    override suspend fun getRooms(): List<Room> {
        return mapper.map(api.getRooms())
    }
}