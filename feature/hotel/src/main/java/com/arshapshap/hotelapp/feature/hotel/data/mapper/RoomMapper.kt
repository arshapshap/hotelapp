package com.arshapshap.hotelapp.feature.hotel.data.mapper

import com.arshapshap.hotelapp.feature.hotel.data.network.room.response.RoomResponse
import com.arshapshap.hotelapp.feature.hotel.data.network.room.response.RoomsResponse
import com.arshapshap.hotelapp.feature.hotel.domain.model.Room

internal class RoomMapper() {

    fun map(roomsResponse: RoomsResponse): List<Room> = roomsResponse.rooms.map { map(it) }

    private fun map(roomResponse: RoomResponse): Room = with(roomResponse) {
        Room(
            id = id ?: 0,
            name = name ?: "",
            price = price ?: 0,
            pricePer = pricePer ?: "",
            peculiarities = peculiarities?.map { it ?: "" } ?: listOf(),
            imageUrls = imageUrls?.map { it ?: "" } ?: listOf()
        )
    }
}