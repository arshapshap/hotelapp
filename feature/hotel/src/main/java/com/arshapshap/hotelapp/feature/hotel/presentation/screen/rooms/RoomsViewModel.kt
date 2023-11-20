package com.arshapshap.hotelapp.feature.hotel.presentation.screen.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.hotelapp.core.presentation.BaseViewModel
import com.arshapshap.hotelapp.feature.hotel.FeatureHotelRouter
import com.arshapshap.hotelapp.feature.hotel.domain.model.Room
import com.arshapshap.hotelapp.feature.hotel.domain.usecase.GetRoomsUseCase
import kotlinx.coroutines.launch

internal class RoomsViewModel(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val router: FeatureHotelRouter
) : BaseViewModel() {

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> = _rooms

    fun loadData() {
        viewModelScope.launch {
            val rooms = getRoomsUseCase()
            _rooms.postValue(rooms)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun openDetails(roomId: Int) { }

    fun selectRoom(roomId: Int) {
        router.openBookingScreen(roomId)
    }
}