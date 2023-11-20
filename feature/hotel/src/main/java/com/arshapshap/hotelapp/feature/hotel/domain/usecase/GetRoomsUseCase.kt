package com.arshapshap.hotelapp.feature.hotel.domain.usecase

import com.arshapshap.hotelapp.feature.hotel.domain.model.Room
import com.arshapshap.hotelapp.feature.hotel.domain.repository.RoomRepository

internal class GetRoomsUseCase(
    private val repository: RoomRepository
) {

    suspend operator fun invoke(): List<Room> {
        return repository.getRooms()
    }
}