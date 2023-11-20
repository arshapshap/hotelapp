package com.arshapshap.hotelapp.feature.hotel.data.network.room.response

import com.google.gson.annotations.SerializedName

internal data class RoomsResponse(
    @SerializedName("rooms")
    val rooms: List<RoomResponse>,
)
